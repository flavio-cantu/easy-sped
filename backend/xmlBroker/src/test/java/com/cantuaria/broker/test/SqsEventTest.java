package com.cantuaria.broker.test;

import com.cantuaria.broker.StubLocalConfiguration;
import com.cantuaria.broker.consumer.SqsConsumerService;
import com.cantuaria.fiscal.FiscalNoteRepository;
import com.cantuaria.fiscal.NotaFiscal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles({"test", "sqs"})
public class SqsEventTest {

    @Autowired
    private StubTestConfiguration stub;

    @Autowired
    private SqsConsumerService sqsConsumerService;
    @Autowired
    private FiscalNoteRepository fiscalNoteRepository;

    @BeforeEach
    public void setup(){
        fiscalNoteRepository.findAll()
                .forEach(notaFiscal ->
                        fiscalNoteRepository.delete(notaFiscal));
    }

    @Test
    void nfEventMustBeRecorded() throws IOException {
        InputStream resourceAsStream = StubLocalConfiguration.class.getResourceAsStream("/bucket/xml-formatado.xml");
        stub.setS3Reponse(ResponseBytes.fromInputStream(GetObjectResponse.builder()
                .build(), resourceAsStream));

        String bodyJsonMessage = new String(Objects.requireNonNull(StubLocalConfiguration.class.getResourceAsStream("/sqs/nf-event.json")).readAllBytes());
        stub.setSqsReponse(
                ReceiveMessageResponse.builder()
                        .messages(Message.builder()
                                .messageId("2206f862-eef6-44a5-9cf9-7202b8aa96b0")
                                .md5OfBody("256a40ba9d5a2d283a1b07bffc060873")
                                .body(bodyJsonMessage)
                                .build())
                        .build()
        );

        sqsConsumerService.pollSqsMessages();

        NotaFiscal notaFiscal = fiscalNoteRepository.findAll().stream().findFirst()
                .orElseThrow(NullPointerException::new);

        assertThat(notaFiscal.getEmit().getCNPJ()).isEqualTo("18571325000190");
        assertThat(notaFiscal.getCaminhoArquivo()).isNotNull();
        assertThat(stub.isMessageDeleted()).isTrue();
    }

    @Test
    void credentialCreationMustBeIgnored() throws IOException {
        stub.setS3Reponse(null);

        String bodyJsonMessage = new String(Objects.requireNonNull(StubLocalConfiguration.class.getResourceAsStream("/sqs/credential-creation.json")).readAllBytes());
        stub.setSqsReponse(
                ReceiveMessageResponse.builder()
                        .messages(Message.builder()
                                .messageId("2206f862-eef6-44a5-9cf9-7202b8aa96b0")
                                .md5OfBody("256a40ba9d5a2d283a1b07bffc060873")
                                .body(bodyJsonMessage)
                                .build())
                        .build()
        );

        sqsConsumerService.pollSqsMessages();

        assertThat(fiscalNoteRepository.findAll()).isEmpty();
        assertThat(stub.isMessageDeleted()).isTrue();
    }

    @Test
    void bucketCreationMustBeIgnored() throws IOException {
        stub.setS3Reponse(null);

        String bodyJsonMessage = new String(Objects.requireNonNull(StubLocalConfiguration.class.getResourceAsStream("/sqs/bucket-creation.json")).readAllBytes());
        stub.setSqsReponse(
                ReceiveMessageResponse.builder()
                        .messages(Message.builder()
                                .messageId("2206f862-eef6-44a5-9cf9-7202b8aa96b0")
                                .md5OfBody("256a40ba9d5a2d283a1b07bffc060873")
                                .body(bodyJsonMessage)
                                .build())
                        .build()
        );

        sqsConsumerService.pollSqsMessages();

        assertThat(fiscalNoteRepository.findAll()).isEmpty();
        assertThat(stub.isMessageDeleted()).isTrue();
    }

    @Test
    void clientSendTestFileMustBeDeletedFromS3() throws IOException {
        stub.setS3Reponse(null);

        String bodyJsonMessage = new String(Objects.requireNonNull(StubLocalConfiguration.class.getResourceAsStream("/sqs/client-test.json")).readAllBytes());
        stub.setSqsReponse(
                ReceiveMessageResponse.builder()
                        .messages(Message.builder()
                                .messageId("2206f862-eef6-44a5-9cf9-7202b8aa96b0")
                                .md5OfBody("256a40ba9d5a2d283a1b07bffc060873")
                                .body(bodyJsonMessage)
                                .build())
                        .build()
        );

        sqsConsumerService.pollSqsMessages();

        assertThat(fiscalNoteRepository.findAll()).isEmpty();
        assertThat(stub.isMessageDeleted()).isTrue();
        assertThat(stub.isS3FileDeleted()).isTrue();
    }

}
