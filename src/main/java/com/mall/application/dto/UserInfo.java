package com.mall.application.dto;

import com.mall.code.RoleType;
import com.mall.code.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Long id;
    private String email;
    private UserStatus status;
    private RoleType role;
}
