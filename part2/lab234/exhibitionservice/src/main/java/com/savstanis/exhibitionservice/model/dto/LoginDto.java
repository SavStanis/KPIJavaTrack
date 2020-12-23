package com.savstanis.exhibitionservice.model.dto;

import com.savstanis.exhibitionservice.model.entity.User;
import lombok.Data;

@Data
public class LoginDto {
    String email;
    String password;
}
