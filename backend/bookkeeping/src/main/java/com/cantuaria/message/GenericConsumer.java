package com.cantuaria.message;

import com.cantuaria.util.MessageException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import java.time.LocalDateTime;
import java.util.function.Consumer;

import static com.cantuaria.message.model.ConsolidateMessage.DESTINATION_QUEUE;

public abstract class GenericConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(GenericConsumer.class);

    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void genericReciveMessage(Message message, int deliveryCount, Consumer<String> consumer) throws JMSException {
        String messageContent = message.getBody(String.class);
        try {
            LOG.info("Receiving({}): {}", DESTINATION_QUEUE, messageContent);
            if (deliveryCount <= 3) {
                consumer.accept(messageContent);
                message.acknowledge();
            } else {
                throw new MessageException("Número máximo de tentativas excedido");
            }
        } catch (Exception e) {
            if (deliveryCount < 3) {
                // Força redelivery
                throw new MessageException(e);
            }
            sendToDlq(DESTINATION_QUEUE, message, messageContent, e.getMessage());
        }
    }


    private void sendToDlq(String originalDestination, Message originalMessage, String message, String errorReason) {
        try {
            jmsTemplate.convertAndSend(originalDestination + ".DQL", message, dlqMessage -> {
                // Adiciona metadados úteis para o retry posterior
                dlqMessage.setStringProperty("ORIGINAL_DESTINATION", originalMessage.getJMSDestination().toString());
                dlqMessage.setStringProperty("ERROR_REASON", errorReason);
                dlqMessage.setStringProperty("ORIGINAL_MESSAGE_ID", originalMessage.getJMSMessageID());
                dlqMessage.setStringProperty("FAILURE_TIMESTAMP", LocalDateTime.now().toString());
                dlqMessage.setIntProperty("REDELIVERY_COUNT", originalMessage.getIntProperty("JMSXDeliveryCount"));
                return dlqMessage;
            });

            //TODO notificar email

        } catch (Exception e) {
            throw new MessageException(e);
        }
    }

}
