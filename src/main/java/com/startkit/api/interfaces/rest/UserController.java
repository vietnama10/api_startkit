package com.startkit.api.interfaces.rest;

import com.startkit.api.application.dto.UserDTO;
import com.startkit.api.application.service.IUserService;
import com.startkit.api.application.service.MessageService;
import com.startkit.api.application.service.UserServiceImpl;
import com.startkit.api.application.dto.ApiResponse;
import com.startkit.api.application.dto.ApiPagedResponse;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final IUserService userService;
    private final MessageService messageService;

    public UserController(
        UserServiceImpl userService,
        MessageService messageService
    ) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping
    @Cacheable("users")
    public ResponseEntity<ApiPagedResponse<UserDTO>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<UserDTO> users = userService.getAllUsers(name, email, page, size);
        ApiPagedResponse<UserDTO> pageResponse = new ApiPagedResponse<UserDTO>();
        pageResponse.setStatus(HttpStatus.OK);
        pageResponse.setMessage(messageService.getMessage("user.retrieved_success"));
        pageResponse.setData(users);
        return ResponseEntity.ok(pageResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.findById(id);
        ApiResponse<UserDTO> response = new ApiResponse<>(HttpStatus.OK, messageService.getMessage("user.retrieved_success"), userDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        ApiResponse<UserDTO> response = new ApiResponse<>(HttpStatus.CREATED, messageService.getMessage("user.created_success"), createdUser);
        return ResponseEntity.created(URI.create("/api/users/" + createdUser.getId())).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        ApiResponse<UserDTO> response = new ApiResponse<>(HttpStatus.OK, messageService.getMessage("user.updated_success"), updatedUser);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        ApiResponse<UserDTO> response = new ApiResponse<>(HttpStatus.OK, messageService.getMessage("user.deleted_success"), null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/auth")
    public Map<String, Object> getUserInfo(@AuthenticationPrincipal Jwt jwt) {
        return jwt.getClaims();
    }
}
