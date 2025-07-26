package com.startkit.api.application.service.query;

import com.startkit.api.application.dto.UserSearchParams;
import com.startkit.api.application.dto.ApiPageResponse;
import com.startkit.api.domain.dao.UserDao;
import com.startkit.api.domain.model.User;
import com.startkit.api.infrastructure.i18n.MessageService;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class SearchUserQueryHandler {
    private final UserDao userDao;
    private final MessageService messageService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public SearchUserQueryHandler(UserDao userDao, MessageService messageService, RedisTemplate<String, Object> redisTemplate) {
        this.userDao = userDao;
        this.messageService = messageService;
        this.redisTemplate = redisTemplate;
    }

    public ApiPageResponse<List<User>> handle(UserSearchParams userSearchParams) {
        if (userSearchParams == null) {
            userSearchParams = new UserSearchParams();
        }
        // String key = "users:" + userSearchParams.getPage() + ":" + userSearchParams.getSize();
        // Object cached = redisTemplate.opsForValue().get(key);
        // List<User> users;
        // if (cached == null) {
        //     users = userDao.searchUsers(userSearchParams);
        //     redisTemplate.opsForValue().set(key, users, 30, TimeUnit.SECONDS);
        // } else {
        //     users = objectMapper.convertValue(cached, new TypeReference<List<User>>() {});
        // }
        List<User> users = userDao.searchUsers(userSearchParams);
        ApiPageResponse<List<User>> pageResponse = new ApiPageResponse<>();
        pageResponse.setStatus(HttpStatus.OK);
        pageResponse.setMessage(messageService.getMessage("user.retrieved_success"));
        pageResponse.setData(users);
        pageResponse.setPage(userSearchParams.getPage());
        pageResponse.setSize(userSearchParams.getSize());
        pageResponse.setTotalElements(userSearchParams.getTotalElements());
        pageResponse.setTotalPages(userSearchParams.getTotalPages());
        return pageResponse;
    }
} 