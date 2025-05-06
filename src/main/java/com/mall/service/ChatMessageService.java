package com.mall.service;

import com.mall.application.dto.ChatMessageDto;
import com.mall.application.message.ChatMessage;

import java.util.List;

public interface ChatMessageService {
    List<ChatMessageDto> fetchMessages(Long roomId);
    ChatMessageDto save(ChatMessage message);
}
