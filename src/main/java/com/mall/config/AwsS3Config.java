package com.mall.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.mall.config.property.AwsS3Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AwsS3Properties.class)
@RequiredArgsConstructor
public class AwsS3Config {
    private final AwsS3Properties awsS3Properties;

    @Bean
    public AmazonS3 amazonS3Client() {
        AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard()
                .withRegion(awsS3Properties.getRegion())
                .enablePathStyleAccess();

        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsS3Properties.getCredential().getAccessKey(), awsS3Properties.getCredential().getSecretKey());
        builder.withCredentials(new AWSStaticCredentialsProvider(awsCredentials));

        return builder.build();
    }
}
