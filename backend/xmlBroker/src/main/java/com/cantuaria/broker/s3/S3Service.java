package com.cantuaria.broker.s3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;


@Component
public class S3Service {
    private static final Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;


    public String getFileContent(String key) {
        try {
            LOG.info("Baixando arquivo do S3: {}/{}", bucketName, key);

            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(getObjectRequest);
            String content = objectBytes.asUtf8String();

            LOG.debug("Conteúdo do arquivo {}: {} bytes", key, content.length());
            return content;

        } catch (S3Exception e) {
            if (e.statusCode() == 404) {
                LOG.error("Arquivo não encontrado no S3: {}", key);
                throw new IllegalArgumentException("Arquivo não encontrado: " + key, e);
            }
            LOG.error("Erro ao acessar S3 (código {}): {}", e.statusCode(), e.getMessage());
            throw new RuntimeException("Falha ao recuperar arquivo do S3", e);
        }
    }

    public void deleteFile(String s3Key) {
        try {
            LOG.info("Deletando arquivo do S3: {}/{}", bucketName, s3Key);

            s3Client.deleteObject(DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(s3Key)
                    .build());

            LOG.debug("Arquivo {} deletado com sucesso", s3Key);
        } catch (S3Exception e) {
            if (e.statusCode() == 404) {
                LOG.error("Arquivo não encontrado no S3: {}", s3Key);
                throw new IllegalArgumentException("Arquivo não encontrado: " + s3Key, e);
            }
            LOG.error("Erro ao deletar arquivo do S3 (código {}): {}", e.statusCode(), e.getMessage());
            throw new RuntimeException("Falha ao deletar arquivo do S3", e);
        }
    }
}
