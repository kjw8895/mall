package com.mall.application.vo;

import com.mall.domain.ChatRoomEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatRoomVo {
    private ChatRoomEntity chatRoom;
    private String sellerName;
    private String buyerName;
}
