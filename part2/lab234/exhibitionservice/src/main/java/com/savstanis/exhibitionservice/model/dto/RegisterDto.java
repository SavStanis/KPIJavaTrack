package com.savstanis.exhibitionservice.model.dto;

import com.savstanis.exhibitionservice.model.entity.User;
import lombok.Data;

@Data
public class RegisterDto {
    String email;
    String password;
    String name;

    public User toUser() {
        return new User().builder()
                .setEmail(email)
                .setName(name)
                .setPassword(password)
                .build();
    }
}
