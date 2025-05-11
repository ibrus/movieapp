package com.pet.notificationservice.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

@Configuration
public class SesConfig {

    @Bean
    public SesClient sesClient(AwsSesProperties awsSesProperties) {
        return SesClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(
                                awsSesProperties.getAccessKey(),
                                awsSesProperties.getSecretKey()
                        )))
                .region(Region.of(awsSesProperties.getRegion()))
                .build();
    }

    @PostConstruct
    public void logInit() {
        System.out.println("[SesConfig] Initialized " + this.getClass().getSimpleName());
    }
}
