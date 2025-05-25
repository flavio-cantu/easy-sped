package com.cantuaria.broker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.function.Consumer;

@Profile("local")
@Configuration
public class StubLocalConfiguration {

    @Bean
    @Primary
    public S3Client s3Client() {
        return new S3Client() {
            @Override
            public String serviceName() {
                return "";
            }

            @Override
            public void close() {
            }

            @Override
            public ResponseBytes<GetObjectResponse> getObjectAsBytes(GetObjectRequest getObjectRequest) throws AwsServiceException, SdkClientException {
                InputStream resourceAsStream = StubLocalConfiguration.class.getResourceAsStream("/bucket/xml-formatado.xml");
                return ResponseBytes.fromInputStream(GetObjectResponse.builder()
                        .build(), resourceAsStream);
            }
        };
    }

    @Bean
    public SqsClient sqsClient() {
        return new SqsClient() {
            @Override
            public String serviceName() {
                return "";
            }

            @Override
            public void close() {
            }

            @Override
            public ReceiveMessageResponse receiveMessage(ReceiveMessageRequest receiveMessageRequest) throws AwsServiceException, SdkClientException {
                try {
                    String bodyJsonMessage = new String(Objects.requireNonNull(StubLocalConfiguration.class.getResourceAsStream("/sqs/nf-event.json")).readAllBytes());
                    return ReceiveMessageResponse.builder()
                            .messages(Message.builder()
                                    .messageId("2206f862-eef6-44a5-9cf9-7202b8aa96b0")
                                    .md5OfBody("256a40ba9d5a2d283a1b07bffc060873")
                                    .body(bodyJsonMessage)
                                    .build())
                            .build();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public DeleteMessageResponse deleteMessage(Consumer<DeleteMessageRequest.Builder> deleteMessageRequest) throws AwsServiceException, SdkClientException {
                return DeleteMessageResponse.builder().build();
            }
        };
    }

}
