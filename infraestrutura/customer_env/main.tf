locals {
  # Usa o nome do workspace como sufixo para evitar conflitos
  workspace_suffix = terraform.workspace == "default" ? "" : "${terraform.workspace}"

  client_name = "concept@${local.workspace_suffix}"
  folder      = local.workspace_suffix

  bucket_name = "poc-concept-b4372ec472133e4363fc" # Bucket compartilhado entre workspaces

  common_tags = {
    type      = "POC",
    company   = "EMPRESA"
    client    = local.client_name
    workspace = terraform.workspace
  }
}

data "aws_region" "current" {
  #get region
}

data "aws_s3_bucket" "existing_bucket" {
  bucket = local.bucket_name
}


resource "aws_iam_user" "client_user" {
  name = local.client_name
  tags = local.common_tags
}


resource "aws_iam_user_policy" "client_s3_upload_policy" {
  name = "client-s3-upload-policy"
  user = aws_iam_user.client_user.name

  policy = jsonencode({
    Version = "2012-10-17",
    Statement = [
      {
        Effect = "Allow",
        Action = [
          "s3:PutObject"
        ],
        Resource = [
          "${data.aws_s3_bucket.existing_bucket.arn}/${local.folder}",
          "${data.aws_s3_bucket.existing_bucket.arn}/${local.folder}/*.pdf",
          "${data.aws_s3_bucket.existing_bucket.arn}/${local.folder}/*.xml"
        ]
      }
    ]
  })
}

# Cria as credenciais de acesso programático
resource "aws_iam_access_key" "client_access_key" {
  user = aws_iam_user.client_user.name
}

# Outputs
output "client_credentials" {
  sensitive = true
  value = {
    access_key      = aws_iam_access_key.client_access_key.id
    secret_key      = aws_iam_access_key.client_access_key.secret
    bucket_name     = data.aws_s3_bucket.existing_bucket.bucket
    folder_path     = "${local.folder}/"
    credential_file = "s3://${data.aws_s3_bucket.existing_bucket.bucket}/credential/${local.folder}/upload.config"
  }
}

output "bucket_found" {
  value = data.aws_s3_bucket.existing_bucket.bucket
}
