package com.cantuaria.broker.consumer;

import com.cantuaria.broker.processor.XmlProcessorService;
import com.cantuaria.broker.s3.S3Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

@Service
@Profile("sqs")
public class SqsConsumerService {

    private static final Logger LOG = LoggerFactory.getLogger(SqsConsumerService.class);

    @Autowired
    private SqsClient sqsClient;
    @Autowired
    private S3Service s3Service;
    @Autowired
    private XmlProcessorService xmlProcessorService;

    @Value("${aws.sqs.queue-url}")
    private String queueUrl;

    @Scheduled(fixedDelay = 5000) // Verifica a cada 5 segundos
    public void pollSqsMessages() {
        try {
            ReceiveMessageRequest request = ReceiveMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .maxNumberOfMessages(10)
                    .build();

            List<Message> messages = sqsClient.receiveMessage(request).messages();

            for (Message message : messages) {
                processMessage(message);
                deleteMessage(message);
            }
        } catch (Exception e) {
            LOG.error("Erro ao processar mensagens SQS", e);
        }
    }

    private void processMessage(Message message) throws JsonProcessingException {
        String s3Key = extractS3KeyFromMessage(message.body());
        if(mustProcess(s3Key)){
            if(clientTestFile(s3Key)) {
                String xmlContent = s3Service.getFileContent(s3Key);
                xmlProcessorService.processXml(xmlContent, s3Key);
            }
        }
    }

    private boolean clientTestFile(String s3Key) {
        if(s3Key.contains("test.xml")){
            s3Service.deleteFile(s3Key);
            return false;
        }
        return true;
    }

    private boolean mustProcess(String s3Key) {
        return !s3Key.contains("credential")
                && !s3Key.isEmpty();
    }

    private String extractS3KeyFromMessage(String messageBody) throws JsonProcessingException {
        JsonNode jsonNode = new ObjectMapper().readTree(messageBody);
        return jsonNode.path("Records").path(0).path("s3").path("object").path("key").asText();
    }

    private void deleteMessage(Message message) {
        sqsClient.deleteMessage(builder -> builder
                .queueUrl(queueUrl)
                .receiptHandle(message.receiptHandle()));
    }
}