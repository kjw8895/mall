package com.mall.service;

import com.mall.application.dto.OrderPaymentDto;

public interface OrderPaymentService {
    void pay(OrderPaymentDto dto);
}
