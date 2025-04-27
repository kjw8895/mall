package com.mall.repository.custom.impl;

import com.mall.application.search.ProductSearchDto;
import com.mall.application.vo.ProductUserVo;
import com.mall.domain.ProductEntity;
import com.mall.domain.QProductEntity;
import com.mall.domain.QUserEntity;
import com.mall.repository.custom.CustomProductEntityRepository;
import com.mall.utils.QuerydslPageUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class CustomProductEntityRepositoryImpl extends QuerydslRepositorySupport implements CustomProductEntityRepository {
    private static final QProductEntity PRODUCT = QProductEntity.productEntity;
    private static final QUserEntity USER = QUserEntity.userEntity;

    public CustomProductEntityRepositoryImpl() {
        super(ProductEntity.class);
    }

    @Override
    public Page<ProductUserVo> page(Pageable pageable, ProductSearchDto searchDto) {
        JPQLQuery<ProductUserVo> query = from(PRODUCT)
                .join(PRODUCT.user, USER)
                .where(QuerydslPageUtils.where()
                        .optionalAnd(searchDto.getUserName(), () -> USER.name.containsIgnoreCase(searchDto.getUserName()))
                        .optionalAnd(searchDto.getProductName(), () -> PRODUCT.name.containsIgnoreCase(searchDto.getProductName()))
                )
                .select(Projections.constructor(ProductUserVo.class,
                        PRODUCT,
                        USER
                ));
        return QuerydslPageUtils.page(getQuerydsl(), query, pageable);
    }

    @Override
    public Page<ProductUserVo> mySelling(Pageable pageable, Long userId) {
        JPQLQuery<ProductUserVo> query = from(PRODUCT)
                .join(PRODUCT.user, USER)
                .where(USER.id.eq(userId))
                .select(Projections.constructor(ProductUserVo.class,
                        PRODUCT,
                        USER
                ));
        return QuerydslPageUtils.page(getQuerydsl(), query, pageable);
    }
}
