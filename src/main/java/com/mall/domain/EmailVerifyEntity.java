package com.mall.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EMAIL_VERIFY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailVerifyEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "token")
    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public static EmailVerifyEntity toEntity(String token, UserEntity user) {
        EmailVerifyEntity entity = new EmailVerifyEntity();
        entity.token = token;
        entity.user = user;
        return entity;
    }
}
