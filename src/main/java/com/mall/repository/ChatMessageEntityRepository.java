package com.mall.repository;

import com.mall.domain.ChatMessageEntity;
import com.mall.repository.custom.CustomChatMessageEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageEntityRepository extends JpaRepository<ChatMessageEntity, Long>, CustomChatMessageEntityRepository {
    List<ChatMessageEntity> findByChatRoomIdOrderByCreatedDatetimeDesc(Long roomId);
}
