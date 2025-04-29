package com.mall.application.dto;

import com.mall.code.PurchaseStatus;
import com.mall.domain.ProductPurchaseEntity;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ProductPurchaseDto {
    private Long id;
    private Long productId;
    private UserDto user;
    private BigDecimal price;
    private PurchaseStatus status;
    private LocalDateTime createdDatetime;

    public static ProductPurchaseDto toDto(ProductPurchaseEntity entity) {
        ProductPurchaseDto dto = new ProductPurchaseDto();
        dto.id = entity.getId();
        dto.productId = entity.getProduct().getId();
        dto.user = UserDto.fromUser(entity.getUser());
        dto.price = entity.getPrice();
        dto.status = entity.getStatus();
        dto.createdDatetime = entity.getCreatedDatetime();
        return dto;
    }
}
