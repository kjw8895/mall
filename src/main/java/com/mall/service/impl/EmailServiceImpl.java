package com.mall.service.impl;

import com.mall.config.property.EmailProperties;
import com.mall.domain.EmailVerifyEntity;
import com.mall.domain.UserEntity;
import com.mall.repository.EmailVerifyEntityRepository;
import com.mall.service.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(EmailProperties.class)
public class EmailServiceImpl implements EmailService {
    private final SesClient sesClient;
    private final EmailProperties emailProperties;
    private final EmailVerifyEntityRepository emailVerifyEntityRepository;

    @Override
    @Transactional
    public void sendVerificationEmail(UserEntity user, String token) {
        EmailVerifyEntity entity = emailVerifyEntityRepository.save(EmailVerifyEntity.toEntity(token, user));
        String verifyUrl = emailProperties.getVerifyUrl() + token;

        SendEmailRequest request = SendEmailRequest.builder()
                .destination(Destination.builder().toAddresses(user.getEmail()).build())
                .message(Message.builder()
                        .subject(Content.builder().data("회원가입 이메일 인증").build())
                        .body(Body.builder()
                                .text(Content.builder()
                                        .data("이메일 인증을 완료하려면 아래 링크를 클릭하세요:\n" + verifyUrl)
                                        .build())
                                .build())
                        .build())
                .source(emailProperties.getSourceEmail())
                .build();

        sesClient.sendEmail(request);
    }
}
