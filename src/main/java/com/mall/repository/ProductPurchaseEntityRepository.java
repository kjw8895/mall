package com.mall.repository;

import com.mall.code.PurchaseStatus;
import com.mall.domain.ProductPurchaseEntity;
import com.mall.repository.custom.CustomProductPurchaseEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductPurchaseEntityRepository extends JpaRepository<ProductPurchaseEntity, Long>, CustomProductPurchaseEntityRepository {
    List<ProductPurchaseEntity> findAllByProductId(Long productId);
    Optional<ProductPurchaseEntity> findFirstByProductIdAndStatus(Long productId, PurchaseStatus status);
}
