package com.mall.service;

import com.mall.application.dto.LoginDto;
import com.mall.application.dto.SignUpDto;
import com.mall.application.dto.TokenDto;
import com.mall.application.dto.UserDto;

public interface AuthService {
    TokenDto login(LoginDto loginDto);
    UserDto signUp(SignUpDto signUpDto);
    boolean checkNickName(String nickName);
}
