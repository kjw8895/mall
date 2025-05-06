package com.mall.repository;

import com.mall.domain.ChatRoomEntity;
import com.mall.repository.custom.CustomChatRoomEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomEntityRepository extends JpaRepository<ChatRoomEntity, Long>, CustomChatRoomEntityRepository {
    Optional<ChatRoomEntity> findByProductIdAndSellerIdAndBuyerId(Long productId, Long sellerId, Long buyerId);
}
