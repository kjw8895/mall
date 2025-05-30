package com.mall.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PurchaseStatus implements CodeEnum {
    OPEN("OPEN", "입찰 완료"),
    PAID("PAID", "결제 완료"),
    AWARDED("AWARDED", "낙찰"),
    COMPLETED("COMPLETED", "거래 완료");

    private final String code;
    private final String text;
}
