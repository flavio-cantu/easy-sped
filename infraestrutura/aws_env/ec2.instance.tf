
#EC2 BASE IMAGE =============
data "aws_ami" "ec2_base_image" {
  most_recent = true

  filter {
    name   = "name"
    values = ["ubuntu/images/hvm-ssd/ubuntu-focal-20.04-amd64-server-*"]
  }

  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }

  owners = ["099720109477"]
}

#EC2 Inscante =============
resource "aws_instance" "poc_instance" {
  ami           = data.aws_ami.ec2_base_image.id
  instance_type = "t2.micro"
  #instance_type          = "t2.medium"
  vpc_security_group_ids = [aws_security_group.poc_concept_sg.id]
  iam_instance_profile   = aws_iam_instance_profile.profile_para_ec2.name # Anexa o perfil
  tags = merge(
    local.common_tags,
    { "Name" = "POC_INSTANCE",
    "Node" = "MicroK8s-Master" }
  )
  depends_on = [
    aws_s3_bucket.bucket-poc,
    aws_sqs_queue.s3_event_queue,
    aws_db_instance.poc_database,
    null_resource.upload_kubernetes_config,
    null_resource.upload_backend
  ]
  #Logfile: tail -f /var/log/cloud-init-output.log
  user_data = <<-EOF
            #cloud-config
            package_update: true
            packages:
                - awscli
            write_files:
              - path: /etc/environment
                content: |
                  S3_BUCKET=${aws_s3_bucket.bucket-poc.bucket}
                  SQS_QUEUE_URL=${aws_sqs_queue.s3_event_queue.url}
                  POSTGRES_HOST=${aws_db_instance.poc_database.address}
                  POSTGRES_PORT=${aws_db_instance.poc_database.port}
                  POSTGRES_DB=${aws_db_instance.poc_database.db_name}
                  POSTGRES_USER=${aws_db_instance.poc_database.username}
                  POSTGRES_PASSWORD=${aws_db_instance.poc_database.password}
                  AWS_REGION=${data.aws_region.current.name}
            runcmd:
              - source /etc/environment
              - snap install microk8s --classic
              - usermod -a -G microk8s ubuntu
              - newgrp microk8s
              - snap alias microk8s.kubectl kubectl
              - microk8s status --wait-ready
              - microk8s enable dns ingress registry    
              - while [ ! -f /usr/bin/aws ]; do sleep 2; done  # Garante a presença do AWS-CLI
              - aws s3 cp s3://${aws_s3_bucket.bucket-poc.bucket}/app /app --recursive
              - aws sqs purge-queue --queue-url ${aws_sqs_queue.s3_event_queue.url}
              - chown ubuntu:ubuntu -R /app
              - kubectl create configmap app-config --from-literal=S3_BUCKET="${aws_s3_bucket.bucket-poc.bucket}" 
                --from-literal=SQS_QUEUE_URL="${aws_sqs_queue.s3_event_queue.url}" 
                --from-literal=POSTGRES_HOST="${aws_db_instance.poc_database.address}" 
                --from-literal=POSTGRES_PORT="${aws_db_instance.poc_database.port}" 
                --from-literal=POSTGRES_DB="${aws_db_instance.poc_database.db_name}"
                --from-literal=AWS_REGION="${data.aws_region.current.name}"
              - kubectl create secret generic db-credentials 
                --from-literal=POSTGRES_USER="${aws_db_instance.poc_database.username}" 
                --from-literal=POSTGRES_PASSWORD="${aws_db_instance.poc_database.password}"
              - kubectl apply -f /app/config/backend/xmlBroker/deployment.yml
            EOF

}


# EC2 Security Group =========
resource "aws_security_group" "poc_concept_sg" {
  name        = "poc-ssh-http"
  description = "Acesso SSH e HTTP para instancia da POC"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"] # Meu IP atual
  }

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"] # Meu IP atual
  }

  ingress {
    from_port   = 6443
    to_port     = 6443
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"] # Para acesso ao Kubernetes API
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = merge(
    local.common_tags,
    { "Name" = "POC_SG" }
  )
}

#EC2 Role permissions
# 1. Role (papel) com permissão para ler do S3
resource "aws_iam_role" "role_para_ec2" {
  name = "poc-ec2-access-s3"

  # Define que a Role pode ser assumida pela EC2
  assume_role_policy = jsonencode({
    Version = "2012-10-17",
    Statement = [{
      Action    = "sts:AssumeRole",
      Effect    = "Allow",
      Principal = { Service = "ec2.amazonaws.com" }
    }]
  })
}

# 2. Política de permissão para ler o bucket específico
resource "aws_iam_role_policy" "permissao_s3" {
  name = "permission-poc-ec2-read-s3"
  role = aws_iam_role.role_para_ec2.id

  policy = jsonencode({
    Version = "2012-10-17",
    Statement = [{
      Action   = ["s3:ListBucket", "s3:GetObject"],
      Effect   = "Allow",
      Resource = ["${aws_s3_bucket.bucket-poc.arn}", "${aws_s3_bucket.bucket-poc.arn}/*"]
      },
      {
        Action   = ["sqs:receivemessage", "sqs:deletemessage"],
        Effect   = "Allow",
        Resource = "${aws_sqs_queue.s3_event_queue.arn}"
    }]
  })
}

# 3. Instance Profile (associa a Role à EC2)
resource "aws_iam_instance_profile" "profile_para_ec2" {
  name = "profile-ec2-s3"
  role = aws_iam_role.role_para_ec2.name
}
