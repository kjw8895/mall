package com.mall.service.impl;

import com.mall.application.dto.ChatRoomDto;
import com.mall.application.dto.ProductChatRoomDto;
import com.mall.domain.ChatRoomEntity;
import com.mall.repository.ChatRoomEntityRepository;
import com.mall.service.ChatRoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomEntityRepository repository;

    @Override
    public ChatRoomDto getOrCreateChatRoom(Long productId, Long sellerId, Long buyerId) {
        Optional<ChatRoomEntity> optionalChatRoomEntity = repository.findByProductIdAndSellerIdAndBuyerId(productId, sellerId, buyerId);
        if (optionalChatRoomEntity.isPresent()) {
            return ChatRoomDto.of(optionalChatRoomEntity.get());
        } else {
            ChatRoomEntity entity = ChatRoomEntity.of(productId, sellerId, buyerId);
            repository.save(entity);
            return ChatRoomDto.of(entity);
        }
    }

    @Override
    public List<ProductChatRoomDto> getChatRooms(Long userId) {
        return repository.getChatRooms(userId);
    }
}
