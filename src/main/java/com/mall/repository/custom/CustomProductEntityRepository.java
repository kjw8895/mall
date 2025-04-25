package com.mall.repository.custom;

import com.mall.application.search.ProductSearchDto;
import com.mall.application.vo.ProductUserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomProductEntityRepository {
    Page<ProductUserVo> page(Pageable pageable, ProductSearchDto searchDto);
}
