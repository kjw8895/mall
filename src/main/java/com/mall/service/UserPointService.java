package com.mall.service;

import com.mall.code.PointType;

public interface UserPointService {
    Long totalPoint(Long userId);
    void updatePoint(Long userId, Long point, PointType type);
}
