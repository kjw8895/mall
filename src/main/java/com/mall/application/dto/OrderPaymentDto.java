package com.mall.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPaymentDto {
    private String paymentKey;
    private String orderNumber;
    private Long userId;
    private BigDecimal amount;

    public static OrderPaymentDto of(String paymentKey, String orderNumber, Long userId, BigDecimal amount) {
        return new OrderPaymentDto(paymentKey, orderNumber, userId, amount);
    }
}
