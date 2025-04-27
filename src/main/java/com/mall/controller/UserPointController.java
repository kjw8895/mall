package com.mall.controller;

import com.mall.annotation.CurrentUser;
import com.mall.application.dto.UserInfo;
import com.mall.common.CommonResponse;
import com.mall.service.UserPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/point")
@RequiredArgsConstructor
public class UserPointController {
    private final UserPointService userPointService;

    @GetMapping("/total")
    public ResponseEntity<CommonResponse<Long>> getTotalPoint(@CurrentUser UserInfo userInfo) {
        return CommonResponse.ok(userPointService.totalPoint(userInfo.getId()));
    }
}
