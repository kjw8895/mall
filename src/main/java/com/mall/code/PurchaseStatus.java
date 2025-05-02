package com.mall.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PurchaseStatus implements CodeEnum {
    OPEN("OPEN", "입찰 완료"),
    AWARDED("AWARDED", "낙찰");

    private final String code;
    private final String text;
}
