package com.cantuaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppBookkeeping {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "db");
        SpringApplication.run(AppBookkeeping.class, args);
    }


}
