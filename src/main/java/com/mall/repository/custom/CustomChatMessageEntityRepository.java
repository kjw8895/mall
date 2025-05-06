package com.mall.repository.custom;

import com.mall.application.dto.ChatMessageDto;

import java.util.List;

public interface CustomChatMessageEntityRepository {
    List<ChatMessageDto> fetchByRoomId(Long roomId);
}
