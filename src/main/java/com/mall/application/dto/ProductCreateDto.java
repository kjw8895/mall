package com.mall.application.dto;

import com.mall.code.ProductType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductCreateDto {
    private String name;
    private BigDecimal price;
    private ProductType type;
}
