package com.mall.controller;

import com.mall.annotation.CurrentUser;
import com.mall.application.dto.ChatRoomDto;
import com.mall.application.dto.ProductChatRoomDto;
import com.mall.application.dto.UserInfo;
import com.mall.common.CommonResponse;
import com.mall.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/chat/room")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @PostMapping
    public ResponseEntity<CommonResponse<ChatRoomDto>> getOrCreateChatRoom(@RequestBody ChatRoomDto chatRoomDto) {
        ChatRoomDto dto = chatRoomService.getOrCreateChatRoom(chatRoomDto.getProductId(), chatRoomDto.getSellerId(), chatRoomDto.getBuyerId());
        return CommonResponse.ok(dto);
    }

    @GetMapping
    private ResponseEntity<CommonResponse<List<ProductChatRoomDto>>> getAllChatRooms(@CurrentUser UserInfo userInfo) {
        List<ProductChatRoomDto> result = chatRoomService.getChatRooms(userInfo.getId());
        return CommonResponse.ok(result);
    }
}
