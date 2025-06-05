package com.cantuaria.feature;

import com.cantuaria.message.GenericMessageProducer;
import com.cantuaria.util.ParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("feature")
@Configuration
@Getter
public class StubFeatureConfiguration {

    private String lastDestination;
    private String lastMessage;

    @Primary
    @Bean
    public GenericMessageProducer messageProducer(ObjectMapper mapper) {
        return new GenericMessageProducer() {
            @Override
            public void sendMessage(String destination, Object message) {
                try {
                    lastDestination = destination;
                    lastMessage = mapper.writeValueAsString(message);
                } catch (JsonProcessingException e) {
                    throw new ParseException(e);
                }
            }
        };
    }

    public void reset() {
        lastDestination = null;
        lastMessage = null;
    }

}
