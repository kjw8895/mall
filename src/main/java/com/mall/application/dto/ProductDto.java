package com.mall.application.dto;

import com.mall.domain.ProductEntity;
import com.mall.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private UserDto user;

    public static ProductDto toDto(ProductEntity product, UserEntity user) {
        ProductDto dto = new ProductDto();
        dto.id = product.getId();
        dto.name = product.getName();
        dto.price = product.getPrice();
        dto.user = UserDto.fromUser(user);
        return dto;
    }
}
