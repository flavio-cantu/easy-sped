package com.cantuaria.broker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AppXmlBroker {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "aws,db,sqs");
        SpringApplication.run(AppXmlBroker.class, args);
    }


}
