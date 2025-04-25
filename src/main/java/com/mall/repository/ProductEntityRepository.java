package com.mall.repository;

import com.mall.domain.ProductEntity;
import com.mall.repository.custom.CustomProductEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long>, CustomProductEntityRepository {
}
