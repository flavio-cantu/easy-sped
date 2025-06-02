
resource "random_id" "db_password" {
  byte_length = 10
}

resource "aws_db_instance" "poc_database" {
  identifier              = "poc-bd-postgres"
  engine                  = "postgres"
  engine_version          = "17.2"         # Versão mais recente no Free Tier
  instance_class          = "db.t4g.micro" # Única classe elegível para Free Tier
  allocated_storage       = 20             # GB (mínimo 20, máximo 20 para Free Tier)
  max_allocated_storage   = 20             # Não permite auto-scaling (para manter no Free Tier)
  storage_type            = "gp2"
  db_name                 = "poc"
  username                = "pocapp"
  password                = "poc-${random_id.db_password.hex}"
  parameter_group_name    = aws_db_parameter_group.poc_database.name
  skip_final_snapshot     = true  # Importante para ambientes de teste/demo
  publicly_accessible     = false # TODO Depois de configurado alterar
  vpc_security_group_ids  = [aws_security_group.postgres_sg.id]
  db_subnet_group_name    = aws_db_subnet_group.db_subnet.name
  multi_az                = false # Desativado para Free Tier
  backup_retention_period = 0     # Mínimo necessário
  deletion_protection     = false # Facilita a destruição do ambiente
  tags                    = local.common_tags
}

resource "aws_db_parameter_group" "poc_database" {
  name   = "db-postgres-params"
  family = "postgres17"

  parameter {
    name  = "log_statement"
    value = "none"
  }

  parameter {
    name  = "rds.force_ssl"
    value = "0" # Desativado para simplificar conexões (não recomendado para produção)
  }
}

resource "aws_db_subnet_group" "db_subnet" {
  name       = "db-subnet-group"
  subnet_ids = data.aws_subnets.default.ids
  tags       = local.common_tags
}

resource "aws_security_group" "postgres_sg" {
  name        = "postgres-security-group"
  description = "Allow inbound PostgreSQL traffic"

  ingress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"] # Restrinja isso em ambientes reais!
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

data "aws_vpc" "default" {
  default = true
}

data "aws_subnets" "default" {
  filter {
    name   = "vpc-id"
    values = [data.aws_vpc.default.id]
  }
}


output "rds_endpoint" {
  value = aws_db_instance.poc_database.endpoint
}

output "connection_string" {
  value     = "postgresql://${aws_db_instance.poc_database.username}:poc-${random_id.db_password.hex}@${aws_db_instance.poc_database.endpoint}/${aws_db_instance.poc_database.db_name}"
  sensitive = true
}
