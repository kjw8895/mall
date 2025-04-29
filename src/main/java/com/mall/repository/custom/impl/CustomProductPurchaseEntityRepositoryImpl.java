package com.mall.repository.custom.impl;

import com.mall.domain.ProductPurchaseEntity;
import com.mall.domain.QProductPurchaseEntity;
import com.mall.domain.QUserEntity;
import com.mall.repository.custom.CustomProductPurchaseEntityRepository;
import com.mall.utils.QuerydslPageUtils;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class CustomProductPurchaseEntityRepositoryImpl extends QuerydslRepositorySupport implements CustomProductPurchaseEntityRepository {
    private static final QProductPurchaseEntity PRODUCT_PURCHASE = QProductPurchaseEntity.productPurchaseEntity;
    private static final QUserEntity USER = QUserEntity.userEntity;

    public CustomProductPurchaseEntityRepositoryImpl() {
        super(ProductPurchaseEntity.class);
    }

    @Override
    public Page<ProductPurchaseEntity> findAllByUserId(Long userId, Pageable pageable) {
        JPQLQuery<ProductPurchaseEntity> query = from(PRODUCT_PURCHASE)
                .join(PRODUCT_PURCHASE.user, USER)
                .where(USER.id.eq(userId));
        return QuerydslPageUtils.page(getQuerydsl(), query, pageable);
    }
}
