package com.mall.service;

import com.mall.application.dto.ProductPurchaseDto;
import com.mall.domain.ProductPurchaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductPurchaseService {
    Page<ProductPurchaseDto> myPurchaseList(Long userId, Pageable pageable);
    List<ProductPurchaseDto> purchaseList(Long productId);
    Optional<ProductPurchaseEntity> findByProductId(Long productId);
    ProductPurchaseDto save(BigDecimal price, Long productId, Long userId);
    ProductPurchaseDto awarded(Long productId, Long id);
    void delete(Long id);
    void deleteByProductId(Long productId);
}
