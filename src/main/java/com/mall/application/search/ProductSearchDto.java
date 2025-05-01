package com.mall.application.search;

import com.mall.code.ProductType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchDto {
    public ProductType type;
    public String userName;
    public String productName;
}
