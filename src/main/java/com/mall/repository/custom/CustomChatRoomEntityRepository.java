package com.mall.repository.custom;

import com.mall.application.dto.ProductChatRoomDto;
import com.mall.application.vo.ChatRoomVo;

import java.util.List;
import java.util.Optional;

public interface CustomChatRoomEntityRepository {
    Optional<ChatRoomVo> fetchById(Long id);
    List<ProductChatRoomDto> getChatRooms(Long userId);
}
