package com.mall.controller;

import com.mall.application.dto.ChatMessageDto;
import com.mall.application.message.ChatMessage;
import com.mall.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage message) {
        // DB 저장
        ChatMessageDto dto = chatMessageService.save(message);

        // 특정 상품방에 메시지 전송
        messagingTemplate.convertAndSend("/topic/chat." + message.getRoomId(), dto);
    }
}
