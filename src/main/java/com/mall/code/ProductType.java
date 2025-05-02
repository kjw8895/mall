package com.mall.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductType implements CodeEnum {
    AUCTION("AUCTION", "경매"),
    NORMAL("NORMAL", "일반");

    private final String code;
    private final String text;
}
