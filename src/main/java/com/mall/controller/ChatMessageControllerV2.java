package com.mall.controller;

import com.mall.application.dto.ChatMessageDto;
import com.mall.common.CommonResponse;
import com.mall.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/chat")
@RequiredArgsConstructor
public class ChatMessageControllerV2 {
    private final ChatMessageService chatMessageService;

    @GetMapping("/room/{roomId}/messages")
    public ResponseEntity<CommonResponse<List<ChatMessageDto>>> getMessages(@PathVariable Long roomId) {
        List<ChatMessageDto> messages = chatMessageService.fetchMessages(roomId);
        return CommonResponse.ok(messages);
    }
}
