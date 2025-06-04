package com.mall.service.impl;

import com.mall.application.dto.LoginDto;
import com.mall.application.dto.SignUpDto;
import com.mall.application.dto.TokenDto;
import com.mall.application.dto.UserDto;
import com.mall.code.UserStatus;
import com.mall.config.JwtTokenProvider;
import com.mall.domain.EmailVerifyEntity;
import com.mall.domain.UserEntity;
import com.mall.exception.CustomException;
import com.mall.repository.EmailVerifyEntityRepository;
import com.mall.repository.UserEntityRepository;
import com.mall.service.AuthService;
import com.mall.service.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserEntityRepository userEntityRepository;
    private final EmailVerifyEntityRepository emailVerifyEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailService emailService;

    @Override
    public TokenDto login(LoginDto loginDto) {
        Optional<UserEntity> optionalUser = userEntityRepository.findByEmail(loginDto.getEmail());
        if (optionalUser.isEmpty()) {
            throw new CustomException("존재하지 않는 이메일입니다.", 404);
        }
        UserEntity user = optionalUser.get();

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new CustomException("비밀번호가 일치하지 않습니다.", 401);
        }

        if (!UserStatus.ACTIVE.equals(user.getStatus())) {
            throw new CustomException("이메일 인증이 필요합니다.", 403);
        }

        String token = jwtTokenProvider.generateAccessToken(user);
        return new TokenDto(token, user.getId(), user.getNickName());
    }

    @Override
    @Transactional
    public UserDto signUp(SignUpDto signUpDto) {
        Optional<UserEntity> optionalUser = userEntityRepository.findByEmail(signUpDto.getEmail());
        if (optionalUser.isPresent()) {
            throw new CustomException("이미 존재하는 이메일입니다.", 404);
        }

        signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        UserEntity user = userEntityRepository.save(UserEntity.of(signUpDto));

        emailService.sendVerificationEmail(user, UUID.randomUUID().toString());

        return new UserDto(user.getId(), user.getEmail(), user.getName(), user.getNickName());
    }

    @Override
    @Transactional
    public boolean verify(String token) {
        EmailVerifyEntity emailVerifyEntity = emailVerifyEntityRepository.findByToken(token).orElseThrow();
        UserEntity user = emailVerifyEntity.getUser();
        user.verify();
        userEntityRepository.save(user);
        return true;
    }

    @Override
    public boolean checkNickName(String nickName) {
        Optional<UserEntity> optional = userEntityRepository.findByNickName(nickName);
        return optional.isPresent();
    }
}
