package com.cantuaria.updater;

import org.springframework.context.annotation.*;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.util.ArrayList;
import java.util.List;

@Profile("local")
@Configuration
@ComponentScan("com.cantuaria.updater")
public class StubLocalConfiguration {

    private static List<String> sendedFiles = new ArrayList<>();

    @Bean
    @Primary
    public S3Client s3Client(){
        return new S3Client() {
            @Override
            public String serviceName() {
                return "";
            }

            @Override
            public void close() {
            }

            public PutObjectResponse putObject(PutObjectRequest putObjectRequest, RequestBody requestBody)
                    throws  AwsServiceException, SdkClientException {
                sendedFiles.add(putObjectRequest.key());
                return  PutObjectResponse.builder()
                        .build();
            }
        };
    }

    public List<String> getSendedFiles() {
        return sendedFiles;
    }
}
