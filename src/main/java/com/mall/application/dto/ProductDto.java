package com.mall.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mall.code.ProductStatus;
import com.mall.code.ProductType;
import com.mall.domain.ProductEntity;
import com.mall.domain.UserEntity;
import com.mall.utils.CodeEnumJsonConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private LocalDateTime createdDatetime;
    @JsonSerialize(using = CodeEnumJsonConverter.Serializer.class)
    private ProductStatus status;
    @JsonSerialize(using = CodeEnumJsonConverter.Serializer.class)
    private ProductType type;
    private UserDto user;

    public static ProductDto toDto(ProductEntity product, UserEntity user) {
        ProductDto dto = new ProductDto();
        dto.id = product.getId();
        dto.name = product.getName();
        dto.price = product.getPrice();
        dto.imageUrl = product.getImageUrl();
        dto.createdDatetime = product.getCreatedDatetime();
        dto.status = product.getStatus();
        dto.type = product.getType();
        dto.user = UserDto.fromUser(user);
        return dto;
    }
}
