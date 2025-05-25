package com.cantuaria.updater.file;

import com.cantuaria.updater.ui.AppTrayIcon;
import com.cantuaria.updater.upload.config.UploaderConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.nio.file.Path;


@Component
public class S3Service {
    private static final Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private S3Client s3;

    private UploaderConfig uploadConfig;
    private boolean s3ConnectionStatus = false;

    public void testConnection(UploaderConfig uploadConfig) {
        this.uploadConfig = uploadConfig;
        try {
            File xmlFile = new File("./test.xml");
            if(!xmlFile.createNewFile()){
                AppTrayIcon.showNotification("Erro conexão servidor", "Não foi possível criar o arquivo de teste da conexão.");
            }
            uploadFile(xmlFile.toPath());
            xmlFile.delete();

            s3ConnectionStatus = true;
        } catch (Exception e) {
            AppTrayIcon.showNotification("Erro S3", "Falha na conexão: " + e.getMessage());
            LOG.warn("S3 was not able to connect", e);
            s3ConnectionStatus = false;
        }
    }

    public void uploadFile(Path file) {
        String key = file.getFileName().toString();
        try {
            RequestBody requestBody = RequestBody.fromFile(file);
            s3.putObject(
                    PutObjectRequest.builder()
                            .bucket(uploadConfig.getBucketName())
                            .key(uploadConfig.getClientFolder()+"/"+key)
                            .build(),
                    requestBody
            );
            if (s3ConnectionStatus) {
                AppTrayIcon.showNotification("Upload OK", "Arquivo enviado: " + key);
            } else {
                AppTrayIcon.showNotification("Upload Stub!", "Arquivo enviado: " + key);
            }
        } catch (Exception e) {
            AppTrayIcon.showNotification("Erro Upload", "Falha ao enviar: " + key);
            throw e;
        }
    }



}
