package com.mall.application.dto;

import com.mall.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private String nickName;

    public static UserDto fromUser(UserEntity user) {
        UserDto dto = new UserDto();
        dto.id = user.getId();
        dto.email = user.getEmail();
        dto.name = user.getName();
        dto.nickName = user.getNickName();
        return dto;
    }
}
