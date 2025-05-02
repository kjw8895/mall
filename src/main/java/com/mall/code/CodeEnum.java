package com.mall.code;

public interface CodeEnum {
    String getText();
    default String getCode() {
        return toString();
    }
}