package com.startkit.api.application.service;

import java.util.List;

import com.startkit.api.application.dto.UserDTO;

public interface IUserService {
    List<UserDTO> getAllUsers(String name, String email, int page, int size);

    UserDTO findById(Long id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);
}
