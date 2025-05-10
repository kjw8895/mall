package com.mall.domain;

import com.mall.application.dto.OrderPaymentDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "ORDER_PAYMENT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderPaymentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "payment_key")
    private String paymentKey;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "amount")
    private BigDecimal amount;

    public static OrderPaymentEntity of(OrderPaymentDto dto) {
        OrderPaymentEntity entity = new OrderPaymentEntity();
        entity.paymentKey = dto.getPaymentKey();
        entity.orderNumber = dto.getOrderNumber();
        entity.userId = dto.getUserId();
        entity.amount = dto.getAmount();
        return entity;
    }
}
