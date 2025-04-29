package com.mall.repository.custom;

import com.mall.domain.ProductPurchaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomProductPurchaseEntityRepository {
    Page<ProductPurchaseEntity> findAllByUserId(Long userId, Pageable pageable);
}
