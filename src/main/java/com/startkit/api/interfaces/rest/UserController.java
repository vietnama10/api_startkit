package com.startkit.api.interfaces.rest;

import com.startkit.api.application.dto.UserSearchParams;
import com.startkit.api.application.service.command.CreateUserCommandHandler;
import com.startkit.api.application.service.query.SearchUserQueryHandler;
import com.startkit.api.domain.model.User;
import com.startkit.api.infrastructure.entity.UserEntity;
import com.startkit.api.infrastructure.i18n.MessageService;
import com.startkit.api.application.dto.ApiResponse;
import com.startkit.api.application.dto.ApiPageResponse;

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
    private final MessageService messageService;
    private final SearchUserQueryHandler searchUserQueryHandler;
    private final CreateUserCommandHandler createUserCommandHandler;

    public UserController(
        MessageService messageService,
        SearchUserQueryHandler searchUserQueryHandler,
        CreateUserCommandHandler createUserCommandHandler
    ) {
        this.messageService = messageService;
        this.searchUserQueryHandler = searchUserQueryHandler;
        this.createUserCommandHandler = createUserCommandHandler;
    }

    @GetMapping
    public ResponseEntity<ApiPageResponse<List<User>>> search(
            @ModelAttribute UserSearchParams userSearchParams) {
        return ResponseEntity.ok(searchUserQueryHandler.handle(userSearchParams));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserEntity>> createUser(@Valid @RequestBody UserEntity user) {
        UserEntity createdUser = createUserCommandHandler.handle(user);
        ApiResponse<UserEntity> response = new ApiResponse<>(HttpStatus.CREATED, messageService.getMessage("user.created_success"), createdUser);
        return ResponseEntity.created(URI.create("/api/users/" + createdUser.getId())).body(response);
    }

    @GetMapping("/auth")
    public Map<String, Object> getUserInfo(@AuthenticationPrincipal Jwt jwt) {
        return jwt.getClaims();
    }
}
