package com.mall.repository;

import com.mall.domain.OrderPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPaymentEntityRepository extends JpaRepository<OrderPaymentEntity, Long> {
}
