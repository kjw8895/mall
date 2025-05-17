package com.mall.service.impl;

import com.mall.application.dto.ProductPurchaseDto;
import com.mall.code.PointType;
import com.mall.code.PurchaseStatus;
import com.mall.domain.ProductEntity;
import com.mall.domain.ProductPurchaseEntity;
import com.mall.domain.UserEntity;
import com.mall.repository.ProductEntityRepository;
import com.mall.repository.ProductPurchaseEntityRepository;
import com.mall.repository.UserEntityRepository;
import com.mall.service.ProductPurchaseService;
import com.mall.service.UserPointService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductPurchaseServiceImpl implements ProductPurchaseService {
    private final ProductPurchaseEntityRepository productPurchaseEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final ProductEntityRepository productEntityRepository;
    private final UserPointService userPointService;

    @Override
    public Page<ProductPurchaseDto> myPurchaseList(Long userId, Pageable pageable) {
        Page<ProductPurchaseEntity> page = productPurchaseEntityRepository.findAllByUserId(userId, pageable);
        return page.map(ProductPurchaseDto::toDto);
    }

    @Override
    public List<ProductPurchaseDto> purchaseList(Long productId) {
        List<ProductPurchaseEntity> entities = productPurchaseEntityRepository.findAllByProductId(productId);

        return entities.stream().map(ProductPurchaseDto::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductPurchaseEntity> findByProductId(Long productId) {
        return productPurchaseEntityRepository.findFirstByProductIdAndStatus(productId, PurchaseStatus.PAID);
    }

    @Override
    public ProductPurchaseDto save(BigDecimal price, Long productId, Long userId) {
        UserEntity user = userEntityRepository.findById(userId).orElseThrow();
        ProductEntity product = productEntityRepository.findById(productId).orElseThrow();
        ProductPurchaseEntity productPurchase = ProductPurchaseEntity.of(user, product, price);
        productPurchase.paid();
        productPurchaseEntityRepository.save(productPurchase);
        return ProductPurchaseDto.toDto(productPurchase);
    }

    @Override
    public ProductPurchaseDto awarded(Long productId, Long id) {
        ProductPurchaseEntity productPurchase = productPurchaseEntityRepository.findById(id).orElseThrow();
        productPurchase.awarded();
        UserEntity user = productPurchase.getUser();
        userPointService.updatePoint(user.getId(), productPurchase.getPrice().longValue(), PointType.LOSE);
        userPointService.updatePoint(productPurchase.getProduct().getUser().getId(), productPurchase.getPrice().longValue(), PointType.EARN);
        productPurchaseEntityRepository.save(productPurchase);
        return ProductPurchaseDto.toDto(productPurchase);
    }

    @Override
    public void delete(Long id) {
        ProductPurchaseEntity entity = productPurchaseEntityRepository.findById(id).orElseThrow();
        productPurchaseEntityRepository.delete(entity);
    }

    @Override
    public void deleteByProductId(Long productId) {
        List<ProductPurchaseEntity> entities = productPurchaseEntityRepository.findAllByProductId(productId);
        productPurchaseEntityRepository.deleteAll(entities);
    }
}
