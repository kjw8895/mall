package com.mall.service.impl;

import com.mall.application.dto.OrderPaymentDto;
import com.mall.application.dto.UserInfo;
import com.mall.application.dto.UserPointDto;
import com.mall.code.PointType;
import com.mall.domain.UserEntity;
import com.mall.domain.UserPointEntity;
import com.mall.repository.UserEntityRepository;
import com.mall.repository.UserPointEntityRepository;
import com.mall.service.OrderPaymentService;
import com.mall.service.UserPointService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
@Transactional
public class UserPointServiceImpl implements UserPointService {
    private final UserPointEntityRepository userPointEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final OrderPaymentService orderPaymentService;

    @Override
    public Long totalPoint(Long userId) {
        AtomicReference<Long> totalPoint = new AtomicReference<>(0L);
        List<UserPointEntity> pointEntityList = userPointEntityRepository.findAllByUserId(userId);
        pointEntityList.forEach(pointEntity -> {
            if (pointEntity.getType().equals(PointType.EARN)) {
                totalPoint.updateAndGet(v -> v + pointEntity.getPoint());
            } else {
                totalPoint.updateAndGet(v -> v - pointEntity.getPoint());
            }
        });
        return totalPoint.get();
    }

    @Override
    public void create(UserPointDto dto, UserInfo userInfo, PointType pointType) {
        orderPaymentService.pay(OrderPaymentDto.of(dto.getPaymentKey(), dto.getOrderId(), userInfo.getId(), BigDecimal.valueOf(dto.getPoint())));
        updatePoint(userInfo.getId(), dto.getPoint(), pointType);
    }

    @Override
    public void updatePoint(Long userId, Long point, PointType type) {
        UserEntity user = userEntityRepository.findById(userId).orElseThrow();
        UserPointEntity entity = UserPointEntity.of(type, point, user);
        userPointEntityRepository.save(entity);
    }
}
