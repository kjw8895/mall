package com.mall.service;

import com.mall.code.PointType;
import com.mall.domain.UserEntity;

public interface UserPointService {
    Long totalPoint(Long userId);
    void updatePoint(UserEntity user, Long point, PointType type);
}
