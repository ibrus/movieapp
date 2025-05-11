package com.pet.notificationservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "aws.ses")
public class AwsSesProperties {
    private String accessKey;
    private String secretKey;
    private String region;
}
