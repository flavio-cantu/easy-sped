locals {
  # Common tags to be assigned to all resources
  common_tags = {
    type    = "POC",
    company = "EMPRESA"
  }
}

locals {
  absolute_path = abspath("${path.module}/../../")
}

data "aws_region" "current" {
  #get region
}


# S3 Bucket ================
resource "random_id" "bucket_suffix" {
  byte_length = 10
}

resource "aws_s3_bucket" "bucket-poc" {
  bucket        = "poc-concept-${random_id.bucket_suffix.hex}"
  force_destroy = true # Permite deletar mesmo com arquivos (apenas para testes)
  tags          = local.common_tags
}



#SQS ==================
resource "aws_sqs_queue" "s3_event_queue" {
  name = "s3-event-queue"
  tags = local.common_tags
}


#Permissão S3 -> SQS
resource "aws_sqs_queue_policy" "s3_to_sqs_policy" {
  queue_url = aws_sqs_queue.s3_event_queue.id

  policy = jsonencode({
    Version = "2012-10-17",
    Statement = [{
      Effect    = "Allow",
      Principal = "*",
      Action    = "sqs:SendMessage",
      Resource  = aws_sqs_queue.s3_event_queue.arn,
      Condition = {
        ArnLike = {
          "aws:SourceArn" = aws_s3_bucket.bucket-poc.arn
        }
      }
    }]
  })
}


#Cria evento S3 -> SQS
resource "aws_s3_bucket_notification" "bucket_notification" {
  bucket = aws_s3_bucket.bucket-poc.id

  queue {
    queue_arn = aws_sqs_queue.s3_event_queue.arn
    events    = ["s3:ObjectCreated:*"] # Todos os eventos de criação
  }

  depends_on = [aws_sqs_queue_policy.s3_to_sqs_policy]
}
