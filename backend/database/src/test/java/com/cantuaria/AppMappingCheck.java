package com.cantuaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppMappingCheck {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "h2");
        SpringApplication.run(AppMappingCheck.class, args);
    }

}
