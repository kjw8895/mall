package com.mall.controller;

import com.mall.annotation.CurrentUser;
import com.mall.application.dto.ProductCreateDto;
import com.mall.application.dto.ProductDto;
import com.mall.application.dto.UserInfo;
import com.mall.application.search.ProductSearchDto;
import com.mall.common.CommonResponse;
import com.mall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/page")
    public ResponseEntity<CommonResponse<Page<ProductDto>>> page(Pageable pageable, ProductSearchDto searchDto) {
        Page<ProductDto> page = productService.page(pageable, searchDto);
        return CommonResponse.ok(page);
    }

    @PostMapping()
    public ResponseEntity<CommonResponse<ProductDto>> save(@CurrentUser UserInfo userInfo, @RequestBody ProductCreateDto dto) {
        ProductDto save = productService.save(userInfo, dto);
        return CommonResponse.ok(save);
    }
}
