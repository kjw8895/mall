package com.mall.service;

import com.mall.application.dto.ProductCreateDto;
import com.mall.application.dto.ProductDto;
import com.mall.application.dto.UserInfo;
import com.mall.application.search.ProductSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    Page<ProductDto> page(Pageable pageable, ProductSearchDto searchDto);
    Page<ProductDto> mySelling(UserInfo userInfo, Pageable pageable);
    ProductDto fetchById(Long id);
    ProductDto save(UserInfo userInfo, ProductCreateDto dto, MultipartFile image, MultipartFile video);
    ProductDto update(UserInfo userInfo, ProductCreateDto dto, MultipartFile image, MultipartFile video, Long id);
    ProductDto pay(UserInfo userInfo, Long id);
    ProductDto complete(UserInfo userInfo, Long id);
    void deleteById(Long id);
}
