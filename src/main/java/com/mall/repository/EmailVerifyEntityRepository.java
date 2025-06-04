package com.mall.repository;

import com.mall.domain.EmailVerifyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerifyEntityRepository extends JpaRepository<EmailVerifyEntity, Long> {
    Optional<EmailVerifyEntity> findByToken(String token);
}
