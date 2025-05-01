package com.mall.application.dto;

import com.mall.code.ProductType;
import com.mall.code.PurchaseStatus;
import com.mall.domain.ProductPurchaseEntity;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ProductPurchaseDto {
    private Long id;
    private Long productId;
    private String productName;
    private ProductType productType;
    private UserDto user;
    private BigDecimal price;
    private PurchaseStatus status;
    private LocalDateTime createdDatetime;

    public static ProductPurchaseDto toDto(ProductPurchaseEntity entity) {
        ProductPurchaseDto dto = new ProductPurchaseDto();
        dto.id = entity.getId();
        dto.productId = entity.getProduct().getId();
        dto.productName = entity.getProduct().getName();
        dto.productType = entity.getProduct().getType();
        dto.user = UserDto.fromUser(entity.getUser());
        dto.price = entity.getPrice();
        dto.status = entity.getStatus();
        dto.createdDatetime = entity.getCreatedDatetime();
        return dto;
    }
}
