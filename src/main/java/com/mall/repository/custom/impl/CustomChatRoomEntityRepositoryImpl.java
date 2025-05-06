package com.mall.repository.custom.impl;

import com.mall.application.dto.ProductChatRoomDto;
import com.mall.application.vo.ChatRoomVo;
import com.mall.domain.ChatRoomEntity;
import com.mall.domain.QChatRoomEntity;
import com.mall.domain.QProductEntity;
import com.mall.domain.QUserEntity;
import com.mall.repository.custom.CustomChatRoomEntityRepository;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomChatRoomEntityRepositoryImpl extends QuerydslRepositorySupport implements CustomChatRoomEntityRepository {
    private static final QChatRoomEntity CHAT_ROOM = QChatRoomEntity.chatRoomEntity;
    private static final QUserEntity USER = QUserEntity.userEntity;
    private static final QProductEntity PRODUCT = QProductEntity.productEntity;

    public CustomChatRoomEntityRepositoryImpl() {
        super(ChatRoomEntity.class);
    }


    @Override
    public Optional<ChatRoomVo> fetchById(Long id) {
        QUserEntity buyer = new QUserEntity("buyer");

        ChatRoomVo vo = from(CHAT_ROOM)
                .join(USER).on(CHAT_ROOM.sellerId.eq(USER.id))
                .join(buyer).on(CHAT_ROOM.buyerId.eq(buyer.id))
                .where(CHAT_ROOM.id.eq(id))
                .select(Projections.constructor(ChatRoomVo.class,
                        CHAT_ROOM,
                        USER.nickName,
                        buyer.nickName
                ))
                .fetchFirst();

        return Optional.ofNullable(vo);
    }

    @Override
    public List<ProductChatRoomDto> getChatRooms(Long userId) {
        QUserEntity buyer = new QUserEntity("buyer");

        return from(CHAT_ROOM)
                .join(PRODUCT).on(CHAT_ROOM.productId.eq(PRODUCT.id))
                .join(USER).on(CHAT_ROOM.sellerId.eq(USER.id))
                .join(buyer).on(CHAT_ROOM.buyerId.eq(buyer.id))
                .where(CHAT_ROOM.buyerId.eq(userId)
                        .or(CHAT_ROOM.sellerId.eq(userId)))
                .select(Projections.constructor(ProductChatRoomDto.class,
                        CHAT_ROOM.id,
                        PRODUCT.id,
                        PRODUCT.name,
                        CHAT_ROOM.sellerId,
                        CHAT_ROOM.buyerId,
                        USER.nickName,
                        buyer.nickName,
                        PRODUCT.imageUrl
                        ))
                .fetch();
    }
}
