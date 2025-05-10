package com.mall.service;

import com.mall.application.dto.UserInfo;
import com.mall.application.dto.UserPointDto;
import com.mall.code.PointType;

public interface UserPointService {
    Long totalPoint(Long userId);
    void create(UserPointDto dto, UserInfo userInfo, PointType pointType);
    void updatePoint(Long userId, Long point, PointType type);
}
