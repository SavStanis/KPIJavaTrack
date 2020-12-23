package com.savstanis.exhibitionservice.service.auth;

import com.savstanis.exhibitionservice.model.dto.LoginDto;
import com.savstanis.exhibitionservice.model.dto.RegisterDto;
import com.savstanis.exhibitionservice.model.entity.User;

public interface AuthService {
    User login(LoginDto loginDto);
    boolean register(RegisterDto registerDto);
}
