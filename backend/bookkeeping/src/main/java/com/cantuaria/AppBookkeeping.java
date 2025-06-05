package com.cantuaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppBookkeeping {

    public static void main(String[] args) {
        //TODO configurar o MQ no kubernetes
        System.setProperty("spring.profiles.active", "db,mq");
        SpringApplication.run(AppBookkeeping.class, args);
    }


}
