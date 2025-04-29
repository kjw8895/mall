package com.mall.service;

import com.mall.application.dto.ProductPurchaseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductPurchaseService {
    Page<ProductPurchaseDto> myPurchaseList(Long userId, Pageable pageable);
    List<ProductPurchaseDto> purchaseList(Long productId);
    ProductPurchaseDto save(BigDecimal price, Long productId, Long userId);
    void delete(Long id);
}
