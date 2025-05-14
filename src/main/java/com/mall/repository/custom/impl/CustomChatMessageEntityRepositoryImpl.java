package com.mall.repository.custom.impl;

import com.mall.application.dto.ChatMessageDto;
import com.mall.domain.ChatMessageEntity;
import com.mall.domain.QChatMessageEntity;
import com.mall.domain.QChatRoomEntity;
import com.mall.domain.QUserEntity;
import com.mall.repository.custom.CustomChatMessageEntityRepository;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomChatMessageEntityRepositoryImpl extends QuerydslRepositorySupport implements CustomChatMessageEntityRepository {
    private static final QChatRoomEntity CHAT_ROOM = QChatRoomEntity.chatRoomEntity;
    private static final QChatMessageEntity CHAT_MESSAGE = QChatMessageEntity.chatMessageEntity;
    private static final QUserEntity USER = QUserEntity.userEntity;

    public CustomChatMessageEntityRepositoryImpl() {
        super(ChatMessageEntity.class);
    }

    @Override
    public List<ChatMessageDto> fetchByRoomId(Long roomId) {
        QUserEntity buyer = new QUserEntity("buyer");

        return from(CHAT_ROOM)
                .join(CHAT_ROOM.messages, CHAT_MESSAGE)
                .join(USER).on(CHAT_ROOM.sellerId.eq(USER.id))
                .join(buyer).on(CHAT_ROOM.buyerId.eq(buyer.id))
                .where(CHAT_ROOM.id.eq(roomId))
                .orderBy(CHAT_MESSAGE.createdDatetime.asc())
                .select(Projections.constructor(ChatMessageDto.class,
                        CHAT_MESSAGE.id,
                        CHAT_ROOM.id,
                        CHAT_MESSAGE.senderId,
                        USER.nickName,
                        buyer.nickName,
                        CHAT_MESSAGE.message,
                        CHAT_MESSAGE.createdDatetime
                        ))
                .fetch();
    }
}
