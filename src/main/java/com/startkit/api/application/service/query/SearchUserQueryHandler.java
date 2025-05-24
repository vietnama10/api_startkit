package com.startkit.api.application.service.query;

import com.startkit.api.application.dto.UserSearchParams;
import com.startkit.api.application.dto.ApiPageResponse;
import com.startkit.api.domain.dao.UserDao;
import com.startkit.api.domain.model.User;
import com.startkit.api.infrastructure.i18n.MessageService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchUserQueryHandler {
    private final UserDao userDao;
    private final MessageService messageService;

    public SearchUserQueryHandler(UserDao userDao, MessageService messageService) {
        this.userDao = userDao;
        this.messageService = messageService;
    }

    public ApiPageResponse<List<User>> handle(UserSearchParams userSearchParams) {
        if (userSearchParams == null) {
            userSearchParams = new UserSearchParams();
        }
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