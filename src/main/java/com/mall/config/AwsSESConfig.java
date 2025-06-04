package com.mall.config;

import com.mall.config.property.AwsS3Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

@Configuration
@EnableConfigurationProperties(AwsS3Properties.class)
@RequiredArgsConstructor
public class AwsSESConfig {
    private final AwsS3Properties awsS3Properties;

    @Bean
    public SesClient amazonSimpleEmailService() {
        return SesClient.builder()
                .region(Region.AP_NORTHEAST_2)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(awsS3Properties.getCredential().getAccessKey(), awsS3Properties.getCredential().getSecretKey())
                ))
                .build();
    }
}
