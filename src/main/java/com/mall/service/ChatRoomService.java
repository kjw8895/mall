package com.mall.service;

import com.mall.application.dto.ChatRoomDto;
import com.mall.application.dto.ProductChatRoomDto;

import java.util.List;

public interface ChatRoomService {
    ChatRoomDto getOrCreateChatRoom(Long productId, Long sellerId, Long buyerId);
    List<ProductChatRoomDto> getChatRooms(Long userId);
}
