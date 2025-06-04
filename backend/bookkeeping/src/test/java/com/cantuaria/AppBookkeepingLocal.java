package com.cantuaria;


import org.springframework.boot.SpringApplication;

public class AppBookkeepingLocal {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "local,h2");
        SpringApplication.run(AppBookkeeping.class, args);
    }

}