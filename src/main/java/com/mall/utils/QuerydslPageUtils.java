package com.mall.utils;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;

import java.util.Collections;
import java.util.List;

public class QuerydslPageUtils {

    public static <E> Page<E> page(final Querydsl querydsl, final JPQLQuery<E> query, final Pageable pageable) {
        if (querydsl == null) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0L);
        }

        long total = query.fetchCount();
        final List<E> list = querydsl.applyPagination(pageable, query).fetch();

        return new PageImpl<>(list, pageable, total);
    }

    public static WhereClauseBuilder where() {
        return new WhereClauseBuilder();
    }

}
