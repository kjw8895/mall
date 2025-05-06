package com.mall.application.dto;

import com.mall.application.message.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {
    private Long id;
    private Long roomId;
    private Long senderId;
    private String sellerName;
    private String buyerName;
    private String message;
    private LocalDateTime createDatetime;
}
