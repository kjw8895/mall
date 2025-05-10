package com.mall.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserPointDto {
    private String paymentKey;
    private String orderId;
    private Long point;
}
