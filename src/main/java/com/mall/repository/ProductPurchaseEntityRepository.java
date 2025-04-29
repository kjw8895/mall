package com.mall.repository;

import com.mall.domain.ProductPurchaseEntity;
import com.mall.repository.custom.CustomProductPurchaseEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductPurchaseEntityRepository extends JpaRepository<ProductPurchaseEntity, Long>, CustomProductPurchaseEntityRepository {
    List<ProductPurchaseEntity> findAllByProductId(Long productId);
}
