package com.startkit.api.infrastructure.persistence.repository;

import com.startkit.api.domain.model.User;
import com.startkit.api.infrastructure.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<User> findByEmail(String email);
}
