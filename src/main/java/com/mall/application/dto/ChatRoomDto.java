package com.mall.application.dto;

import com.mall.domain.ChatRoomEntity;
import lombok.Getter;

@Getter
public class ChatRoomDto {
    private Long id;
    private Long productId;
    private Long sellerId;
    private Long buyerId;

    public static ChatRoomDto of(ChatRoomEntity entity) {
        ChatRoomDto dto = new ChatRoomDto();
        dto.id = entity.getId();
        dto.productId = entity.getProductId();
        dto.sellerId = entity.getSellerId();
        dto.buyerId = entity.getBuyerId();
        return dto;
    }
}
