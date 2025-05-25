output "s3_bucket_name" {
  value = aws_s3_bucket.bucket-poc.bucket
}

output "ec2_public_ip" {
  value = aws_instance.poc_instance.public_ip
}

output "sqs_queue_url" {
  value = aws_sqs_queue.s3_event_queue.url
}
