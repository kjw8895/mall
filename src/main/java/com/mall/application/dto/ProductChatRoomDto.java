package com.mall.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductChatRoomDto {
    private Long roomId;
    private Long productId;
    private String productName;
    private Long sellerId;
    private Long buyerId;
    private String sellerName;
    private String buyerName;
    private String imageUrl;
}
