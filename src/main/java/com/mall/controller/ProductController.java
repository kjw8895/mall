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
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<ProductDto>> page(@PathVariable Long id) {
        ProductDto dto = productService.fetchById(id);
        return CommonResponse.ok(dto);
    }

    @GetMapping("/my-selling")
    public ResponseEntity<CommonResponse<Page<ProductDto>>> trade(@CurrentUser UserInfo userInfo, Pageable pageable) {
        Page<ProductDto> page = productService.mySelling(userInfo, pageable);
        return CommonResponse.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<ProductDto>> update(@PathVariable Long id, @CurrentUser UserInfo userInfo, @RequestPart("request") ProductCreateDto dto, @RequestPart(value = "image", required = false) MultipartFile image, @RequestPart(value = "video", required = false) MultipartFile video) {
        ProductDto result = productService.update(userInfo, dto, image, video, id);
        return CommonResponse.ok(result);
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<CommonResponse<ProductDto>> pay(@PathVariable Long id, @CurrentUser UserInfo userInfo) {
        ProductDto result = productService.pay(userInfo, id);
        return CommonResponse.ok(result);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<CommonResponse<ProductDto>> complete(@PathVariable Long id, @CurrentUser UserInfo userInfo) {
        ProductDto result = productService.complete(userInfo, id);
        return CommonResponse.ok(result);
    }

    @PostMapping()
    public ResponseEntity<CommonResponse<ProductDto>> save(@CurrentUser UserInfo userInfo, @RequestPart("request") ProductCreateDto dto, @RequestPart(value = "image") MultipartFile image, @RequestPart(required = false, value = "video") MultipartFile video) {
        ProductDto save = productService.save(userInfo, dto, image, video);
        return CommonResponse.ok(save);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Boolean>> delete(@PathVariable Long id) {
        productService.deleteById(id);
        return CommonResponse.ok(true);
    }
}
