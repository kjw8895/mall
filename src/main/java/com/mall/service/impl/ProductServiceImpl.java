package com.mall.service.impl;

import com.mall.application.dto.ProductCreateDto;
import com.mall.application.dto.ProductDto;
import com.mall.application.dto.UserInfo;
import com.mall.application.search.ProductSearchDto;
import com.mall.application.vo.ProductUserVo;
import com.mall.domain.ProductEntity;
import com.mall.domain.UserEntity;
import com.mall.repository.ProductEntityRepository;
import com.mall.repository.UserEntityRepository;
import com.mall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductEntityRepository productEntityRepository;
    private final UserEntityRepository userEntityRepository;

    @Override
    public Page<ProductDto> page(Pageable pageable, ProductSearchDto searchDto) {
        Page<ProductUserVo> page = productEntityRepository.page(pageable, searchDto);
        return page.map(vo -> ProductDto.toDto(vo.getProduct(), vo.getUser()));
    }

    @Override
    public ProductDto save(UserInfo userInfo, ProductCreateDto dto) {
        UserEntity user = userEntityRepository.findById(userInfo.getId()).orElseThrow(RuntimeException::new);
        ProductEntity product = ProductEntity.of(dto.getName(), dto.getPrice(), user);
        productEntityRepository.save(product);
        return ProductDto.toDto(product, user);
    }
}
