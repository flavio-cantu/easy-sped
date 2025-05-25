package com.cantuaria.broker.test;

import org.springframework.context.annotation.*;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.NoOpTaskScheduler;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.function.Consumer;

@Profile("test")
@Configuration
public class StubTestConfiguration {

    private ResponseBytes<GetObjectResponse> s3Reponse;
    private ReceiveMessageResponse sqsReponse;
    private boolean messageDeleted;
    private boolean s3FileDeleted;

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
                return s3Reponse;
            }

            @Override
            public DeleteObjectResponse deleteObject(DeleteObjectRequest deleteObjectRequest) throws AwsServiceException, SdkClientException {
                s3FileDeleted = true;
                return DeleteObjectResponse.builder().build();
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
                return sqsReponse;
            }

            @Override
            public DeleteMessageResponse deleteMessage(Consumer<DeleteMessageRequest.Builder> deleteMessageRequest) throws AwsServiceException, SdkClientException {
                messageDeleted = true;
                return DeleteMessageResponse.builder().build();
            }
        };
    }

    @Bean
    @Primary
    public TaskScheduler taskScheduler() {
        return new NoOpTaskScheduler();
    }

    public void setS3Reponse(ResponseBytes<GetObjectResponse> s3Reponse) {
        s3FileDeleted = false;
        this.s3Reponse = s3Reponse;
    }

    public void setSqsReponse(ReceiveMessageResponse sqsReponse) {
        messageDeleted = false;
        this.sqsReponse = sqsReponse;
    }

    public boolean isMessageDeleted() {
        return messageDeleted;
    }

    public boolean isS3FileDeleted() {
        return s3FileDeleted;
    }
}
