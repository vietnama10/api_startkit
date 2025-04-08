package com.startkit.api.application.service;

import com.startkit.api.application.dto.UserDTO;
import com.startkit.api.domain.model.User;
import com.startkit.api.domain.repository.UserRepository;
import com.startkit.api.application.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final MessageService messageService;

    public UserServiceImpl(
        MessageService messageService,
        UserRepository userRepository
    ) {
        this.messageService = messageService;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers(String name, String email, int page, int size) {
        List<User> users = userRepository.findAll();  // Chưa có phân trang, cần bổ sung
        return users.stream().map(UserDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageService.getMessage("user.not_found", id)));
        return UserDTO.fromEntity(user);
    }

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        User user = userDTO.toEntity();
        user = userRepository.save(user);
        return UserDTO.fromEntity(user);
    }

    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageService.getMessage("user.not_found", id)));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return UserDTO.fromEntity(userRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageService.getMessage("user.not_found", id)));
        userRepository.delete(user);
    }
}
