package com.mall.repository;

import com.mall.domain.UserPointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPointEntityRepository extends JpaRepository<UserPointEntity, Long> {
    List<UserPointEntity> findAllByUserId(Long userId);
}
