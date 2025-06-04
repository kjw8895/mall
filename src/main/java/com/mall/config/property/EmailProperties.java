package com.mall.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("email")
public class EmailProperties {
    private String verifyUrl;
    private String sourceEmail;
}
