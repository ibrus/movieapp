package com.pet.common.storage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "aws.s3")
public class S3Config {

    private String accessKey;
    private String secretKey;
    private String region;
    private String bucket;
}
