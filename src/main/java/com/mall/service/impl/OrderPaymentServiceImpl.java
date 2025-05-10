package com.mall.service.impl;

import com.mall.application.dto.OrderPaymentDto;
import com.mall.domain.OrderPaymentEntity;
import com.mall.repository.OrderPaymentEntityRepository;
import com.mall.service.OrderPaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderPaymentServiceImpl implements OrderPaymentService {
    private final OrderPaymentEntityRepository repository;

    @Override
    public void pay(OrderPaymentDto dto) {
        OrderPaymentEntity entity = OrderPaymentEntity.of(dto);
        repository.save(entity);
    }
}
