package com.mall.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductStatus implements CodeEnum {
    WAITING("WAITING", "판매중"),
    PAID("PAID", "구매중"),
    COMPLETE("COMPLETE", "거래 완료");

    private final String code;
    private final String text;
}
