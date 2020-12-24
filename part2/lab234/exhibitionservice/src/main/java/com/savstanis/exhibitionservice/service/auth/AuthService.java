package com.savstanis.exhibitionservice.service.auth;

import com.savstanis.exhibitionservice.model.dto.LoginDto;
import com.savstanis.exhibitionservice.model.dto.RegisterDto;
import com.savstanis.exhibitionservice.model.entity.User;

import java.sql.SQLException;

public interface AuthService {
    User login(LoginDto loginDto) throws SQLException;
    boolean register(RegisterDto registerDto) throws SQLException;
}
