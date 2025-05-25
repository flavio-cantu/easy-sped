

resource "null_resource" "upload_kubernetes_config" {
  depends_on = [aws_s3_bucket.bucket-poc]
  provisioner "local-exec" {
    command = <<-EOT
      ../build/microk8s_upload.sh ${aws_s3_bucket.bucket-poc.bucket} config/backend/xmlBroker /workspace/concept-project/infraestrutura/build/microk8s/backend/xmlBroker
    EOT
  }
}

resource "null_resource" "upload_backend" {
  depends_on = [aws_s3_bucket.bucket-poc]
  provisioner "local-exec" {
    command = <<-EOT
      ../build/mvn_install.sh /workspace/concept-project/backend/database
      ../build/mvn_build.sh ${aws_s3_bucket.bucket-poc.bucket} xmlBroker.jar target/Xml-Broker-*.jar /workspace/concept-project/backend/xmlBroker
      ../build/mvn_build.sh ${aws_s3_bucket.bucket-poc.bucket} upload.jar target/cloud-updater-*.jar /workspace/concept-project/backend/upload
    EOT
  }
}
