package com.mall.domain;

import com.mall.application.dto.ProductCreateDto;
import com.mall.code.ProductStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.WAITING;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "image_url")
    private String imageUrl;

    public void update(ProductCreateDto dto) {
        this.name = dto.getName();
        this.price = dto.getPrice();
    }

    public void updateImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static ProductEntity of(String name, BigDecimal price, UserEntity user, String imageUrl) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.name = name;
        productEntity.price = price;
        productEntity.user = user;
        productEntity.imageUrl = imageUrl;
        return productEntity;
    }
}
