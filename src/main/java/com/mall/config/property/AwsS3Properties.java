package com.mall.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("aws.s3")
public class AwsS3Properties {
    private Credential credential;
    private String region;
    private String bucket;
    private String attachmentPath;
    private String baseUrl;

    public String getPath(String fileName) {
        return String.format("%s/%s", attachmentPath, fileName);
    }

    public String getFullPath(String fileName) {
        return String.format("%s/%s/%s", baseUrl, attachmentPath, fileName);
    }

    @Getter
    @Setter
    public static class Credential {
        private String accessKey;
        private String secretKey;
    }
}
