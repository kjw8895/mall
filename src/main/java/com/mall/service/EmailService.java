package com.mall.service;

import com.mall.domain.UserEntity;

public interface EmailService {
    void sendVerificationEmail(UserEntity user, String token);
}
