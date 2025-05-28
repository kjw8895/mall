package com.mall.domain;

import com.mall.application.dto.ProductCreateDto;
import com.mall.code.ProductStatus;
import com.mall.code.ProductType;
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

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.WAITING;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ProductType type;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "video_url")
    private String videoUrl;

    public void pay() {
        this.status = ProductStatus.PAID;
    }

    public void complete() {
        this.status = ProductStatus.COMPLETE;
    }

    public void waiting() {
        this.status = ProductStatus.WAITING;
    }

    public void update(ProductCreateDto dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.price = dto.getPrice();
    }

    public void updateImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void updateVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public static ProductEntity of(String name, String description, BigDecimal price, UserEntity user, String imageUrl, ProductType type) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.name = name;
        productEntity.description = description;
        productEntity.price = price;
        productEntity.user = user;
        productEntity.imageUrl = imageUrl;
        productEntity.type = type;
        return productEntity;
    }
}
