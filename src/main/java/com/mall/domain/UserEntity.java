package com.mall.domain;

import com.mall.application.dto.SignUpDto;
import com.mall.code.RoleType;
import com.mall.code.UserStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickName;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleType role = RoleType.USER;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.WAITING;

    public void verify() {
        this.status = UserStatus.ACTIVE;
    }

    public static UserEntity of(SignUpDto dto) {
        UserEntity entity = new UserEntity();
        entity.name = dto.getName();
        entity.email = dto.getEmail();
        entity.nickName = dto.getNickName();
        entity.password = dto.getPassword();

        return entity;
    }
}

