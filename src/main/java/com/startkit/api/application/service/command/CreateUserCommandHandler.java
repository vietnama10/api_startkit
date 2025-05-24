package com.startkit.api.application.service.command;

import com.startkit.api.infrastructure.entity.UserEntity;
import com.startkit.api.infrastructure.persistence.repository.UserRepository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateUserCommandHandler {
    private final UserRepository userRepository;

    public CreateUserCommandHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserEntity handle(UserEntity user) {
        user = userRepository.save(user);
        return user;
    }
} 