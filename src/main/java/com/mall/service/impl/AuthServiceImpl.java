package com.mall.service.impl;

import com.mall.application.dto.LoginDto;
import com.mall.application.dto.SignUpDto;
import com.mall.application.dto.TokenDto;
import com.mall.application.dto.UserDto;
import com.mall.config.JwtTokenProvider;
import com.mall.domain.UserEntity;
import com.mall.repository.UserEntityRepository;
import com.mall.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public TokenDto login(LoginDto loginDto) {
        Optional<UserEntity> optionalUser = userEntityRepository.findByEmail(loginDto.getEmail());
        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User not found");
        }
        UserEntity user = optionalUser.get();
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }
        String token = jwtTokenProvider.generateAccessToken(user);
        return new TokenDto(token, user.getId(), user.getNickName());
    }

    @Override
    public UserDto signUp(SignUpDto signUpDto) {
        signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        UserEntity user = userEntityRepository.save(UserEntity.of(signUpDto));

        return new UserDto(user.getId(), user.getEmail(), user.getName(), user.getNickName());
    }
}
