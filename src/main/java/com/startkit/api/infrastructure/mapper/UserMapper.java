package com.startkit.api.infrastructure.mapper;

import com.startkit.api.domain.model.User;
import com.startkit.api.infrastructure.entity.UserEntity;

public class UserMapper {
    /**
     * Maps UserEntity to User domain model, omitting the password for security.
     */
    public static User toUser(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new User(
            userEntity.getId(),
            userEntity.getName(),
            userEntity.getEmail()
        );
    }

    /**
     * Maps User domain model to UserEntity, including password for persistence.
     */
    public static UserEntity toUserEntity(User user) {
        if (user == null) {
            return null;
        }
        return new UserEntity(
            user.getId(),
            user.getEmail(),
            user.getName(),
            user.getPassword()
        );
    }
}
