package com.mall.service.impl;

import com.mall.code.PointType;
import com.mall.domain.UserEntity;
import com.mall.domain.UserPointEntity;
import com.mall.repository.UserPointEntityRepository;
import com.mall.service.UserPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class UserPointServiceImpl implements UserPointService {
    private final UserPointEntityRepository userPointEntityRepository;

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
    public void updatePoint(UserEntity user, Long point, PointType type) {
        UserPointEntity entity = UserPointEntity.of(type, point, user);
        userPointEntityRepository.save(entity);
    }
}
