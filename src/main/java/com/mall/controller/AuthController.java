package com.mall.controller;

import com.mall.application.dto.LoginDto;
import com.mall.application.dto.SignUpDto;
import com.mall.application.dto.TokenDto;
import com.mall.application.dto.UserDto;
import com.mall.common.CommonResponse;
import com.mall.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<TokenDto>> login(@RequestBody LoginDto loginDto) {
        TokenDto dto = authService.login(loginDto);
        return CommonResponse.ok(dto);
    }

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<UserDto>> signup(@RequestBody SignUpDto signUpDto) {
        UserDto dto = authService.signUp(signUpDto);
        return CommonResponse.ok(dto);
    }

    @GetMapping("/verify")
    public ResponseEntity<CommonResponse<Boolean>> verify(@RequestParam String token) {
        return CommonResponse.ok(authService.verify(token));
    }

    @GetMapping("/nickName/validation")
    public ResponseEntity<CommonResponse<Boolean>> validateNickName(@RequestParam("nickName") String nickName) {
        boolean result = authService.checkNickName(nickName);
        return CommonResponse.ok(result);
    }
}
