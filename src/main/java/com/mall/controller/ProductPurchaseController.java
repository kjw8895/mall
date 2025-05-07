package com.mall.controller;

import com.mall.annotation.CurrentUser;
import com.mall.application.dto.ProductPurchaseDto;
import com.mall.application.dto.UserInfo;
import com.mall.common.CommonResponse;
import com.mall.service.ProductPurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
@RequiredArgsConstructor
public class ProductPurchaseController {
    private final ProductPurchaseService productPurchaseService;

    @GetMapping("/my-purchase")
    public ResponseEntity<CommonResponse<Page<ProductPurchaseDto>>> getMyPurchase(@CurrentUser UserInfo userInfo, Pageable pageable) {
        Page<ProductPurchaseDto> result = productPurchaseService.myPurchaseList(userInfo.getId(), pageable);
        return CommonResponse.ok(result);
    }

    @DeleteMapping("/purchase/{id}")
    public ResponseEntity<CommonResponse<Boolean>> deletePurchase(@PathVariable Long id) {
        productPurchaseService.delete(id);
        return CommonResponse.ok(true);
    }

    @GetMapping("/{productId}/purchase")
    public ResponseEntity<CommonResponse<List<ProductPurchaseDto>>> getPurchase(@PathVariable Long productId) {
        List<ProductPurchaseDto> result = productPurchaseService.purchaseList(productId);
        return CommonResponse.ok(result);
    }

    @PostMapping("/{productId}/purchase")
    public ResponseEntity<CommonResponse<ProductPurchaseDto>> purchase(@CurrentUser UserInfo userInfo, @PathVariable Long productId, @RequestBody ProductPurchaseDto req) {
        ProductPurchaseDto dto = productPurchaseService.save(req.getPrice(), productId, userInfo.getId());
        return CommonResponse.ok(dto);
    }

    @PutMapping("/{productId}/purchase/{id}")
    public ResponseEntity<CommonResponse<ProductPurchaseDto>> awarded(@CurrentUser UserInfo userInfo, @PathVariable Long productId, @PathVariable Long id) {
        ProductPurchaseDto dto = productPurchaseService.awarded(productId, id);
        return CommonResponse.ok(dto);
    }
}
