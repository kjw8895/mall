package com.mall.service;

import com.mall.application.dto.ProductCreateDto;
import com.mall.application.dto.ProductDto;
import com.mall.application.dto.UserInfo;
import com.mall.application.search.ProductSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductDto> page(Pageable pageable, ProductSearchDto searchDto);
    ProductDto save(UserInfo userInfo, ProductCreateDto dto);
}
