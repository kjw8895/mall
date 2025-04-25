package com.mall.controller;

import com.mall.application.dto.TokenDto;
import com.mall.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    @PostMapping("/login")
    public ResponseEntity<CommonResponse<TokenDto>> login() {
        return null;
    }

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<TokenDto>> signup() {
        return null;
    }
}
