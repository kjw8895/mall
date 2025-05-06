package com.mall.service.impl;

import com.mall.application.dto.ChatMessageDto;
import com.mall.application.message.ChatMessage;
import com.mall.application.vo.ChatRoomVo;
import com.mall.domain.ChatMessageEntity;
import com.mall.repository.ChatMessageEntityRepository;
import com.mall.repository.ChatRoomEntityRepository;
import com.mall.service.ChatMessageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatMessageServiceImpl implements ChatMessageService {
    private final ChatMessageEntityRepository repository;
    private final ChatRoomEntityRepository roomRepository;

    @Override
    public List<ChatMessageDto> fetchMessages(Long roomId) {
        return repository.fetchByRoomId(roomId);
    }

    @Override
    public ChatMessageDto save(ChatMessage message) {
        ChatRoomVo chatRoomVo = roomRepository.fetchById(message.getRoomId()).orElseThrow(RuntimeException::new);
        ChatMessageEntity chatMessage = ChatMessageEntity.of(chatRoomVo.getChatRoom(), message.getSenderId(), message.getMessage());
        repository.save(chatMessage);
        return new ChatMessageDto(chatMessage.getId(), chatRoomVo.getChatRoom().getId(), chatMessage.getSenderId(), chatRoomVo.getSellerName(), chatRoomVo.getBuyerName(), chatMessage.getMessage(), chatMessage.getCreatedDatetime());
    }
}
