package com.cantuaria.message;

import com.cantuaria.util.ParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class GenericMessageProducer {

    private static final Logger LOG = LoggerFactory.getLogger(GenericMessageProducer.class);

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private ObjectMapper mapper;

    public void sendMessage(String destination, Object message) {
        try {
            jmsTemplate.convertAndSend(destination, mapper.writeValueAsString(message));
            LOG.info("Sending({}): {}", destination, message);
        } catch (JsonProcessingException e) {
            throw new ParseException(e);
        }
    }

}
