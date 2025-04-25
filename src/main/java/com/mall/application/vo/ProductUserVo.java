package com.mall.application.vo;

import com.mall.domain.ProductEntity;
import com.mall.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUserVo {
    private ProductEntity product;
    private UserEntity user;
}
