package com.cantuaria.message;

import com.cantuaria.consolidate.ConsolidateService;
import com.cantuaria.message.model.ConsolidateMessage.RequestConsolidate;
import com.cantuaria.util.MessageException;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import static com.cantuaria.message.model.ConsolidateMessage.DESTINATION_QUEUE;

@Component
public class ConsolidationConsumer extends GenericConsumer {

    @Autowired
    private ConsolidateService consolidateService;

    @JmsListener(destination = DESTINATION_QUEUE)
    public void receiveMessage(Message message,
                               @Header(name = "JMSXDeliveryCount", defaultValue = "1") int deliveryCount) throws JMSException {
        genericReciveMessage(message, deliveryCount, messageContent -> {
            try {
                RequestConsolidate requestConsolidate = mapper.readValue(messageContent, RequestConsolidate.class);
                consolidateService.processBookkeeping(requestConsolidate.id());
            } catch (Exception e) {
                throw new MessageException(e);
            }
        });
    }

}
