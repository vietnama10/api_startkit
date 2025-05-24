package com.startkit.api.domain.dao;

import java.util.List;

import com.startkit.api.application.dto.UserSearchParams;
import com.startkit.api.domain.model.User;

public interface UserDao {
    List<User> searchUsers(UserSearchParams params);
}
