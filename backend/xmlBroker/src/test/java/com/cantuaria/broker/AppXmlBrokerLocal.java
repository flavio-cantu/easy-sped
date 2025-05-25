package com.cantuaria.broker;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class AppXmlBrokerLocal {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "local,h2");
        SpringApplication.run(AppXmlBroker.class, args);
    }

}