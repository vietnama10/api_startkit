package com.startkit.api.application.dto;

import com.startkit.api.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;

    public static UserDTO fromEntity(User user) {
        return new UserDTO(user.getUserId(), user.getName(), user.getEmail(), null);
    }

    public User toEntity() {
        return new User(this.id, this.name, this.email, this.password);
    }
}
