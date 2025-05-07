package com.mall.domain;

import com.mall.code.ProductType;
import com.mall.code.PurchaseStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCT_PURCHASE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductPurchaseEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;

    public void awarded() {
        this.status = PurchaseStatus.AWARDED;
        this.product.complete();
    }

    public static ProductPurchaseEntity of(UserEntity user, ProductEntity product, BigDecimal price) {
        ProductPurchaseEntity entity = new ProductPurchaseEntity();
        entity.user = user;
        entity.product = product;
        entity.price = price;
        entity.status = product.getType().equals(ProductType.AUCTION) ? PurchaseStatus.OPEN : PurchaseStatus.PAID;
        return entity;
    }
}
