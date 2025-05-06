package com.mall.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CHAT_MESSAGE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private ChatRoomEntity chatRoom;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "message")
    private String message;

    public static ChatMessageEntity of(ChatRoomEntity chatRoom, Long senderId, String message) {
        ChatMessageEntity entity = new ChatMessageEntity();
        entity.chatRoom = chatRoom;
        entity.senderId = senderId;
        entity.message = message;
        return entity;
    }
}
