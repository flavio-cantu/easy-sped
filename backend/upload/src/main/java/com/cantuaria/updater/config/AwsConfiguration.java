package com.cantuaria.updater.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.services.s3.S3Client;

@Profile("!local")
@Configuration
public class AwsConfiguration {

    @Bean
    public S3Client s3Client(){
        return S3Client.create();
    }

}
