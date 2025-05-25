

resource "null_resource" "upload_credential_config" {
  depends_on = [aws_iam_access_key.client_access_key]
  provisioner "local-exec" {
    command = <<-EOT
      ../build/customer_credentials.sh ${aws_iam_access_key.client_access_key.id} ${aws_iam_access_key.client_access_key.secret} ${data.aws_region.current.name} ${data.aws_s3_bucket.existing_bucket.bucket} ${local.folder}
    EOT
  }
}
