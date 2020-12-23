package com.savstanis.exhibitionservice.service.auth;

import com.savstanis.exhibitionservice.model.ConnectionPoolSupplier;
import com.savstanis.exhibitionservice.model.dao.user.UserDao;
import com.savstanis.exhibitionservice.model.dao.user.UserDaoImpl;
import com.savstanis.exhibitionservice.model.dto.LoginDto;
import com.savstanis.exhibitionservice.model.dto.RegisterDto;
import com.savstanis.exhibitionservice.model.entity.User;

import java.sql.SQLException;
import java.util.Optional;

public class AuthServiceImpl implements AuthService {

    private UserDao userDao;

    public AuthServiceImpl() {
        try {
                this.userDao = new UserDaoImpl(ConnectionPoolSupplier.getDataSource().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User login(LoginDto loginDto) {
        Optional<User> user = userDao.findByEmail(loginDto.getEmail());

        if (user.isPresent() && user.get().getPassword().equals(loginDto.getPassword())) {
            return user.get();
        }
        return null;
    }

    @Override
    public boolean register(RegisterDto registerDto) {
        if (userDao.findByEmail(registerDto.getEmail()).isPresent()) {
            return false;
        }

        User newUser = registerDto.toUser();
        newUser.setRole("user");
        userDao.create(newUser);

        return true;
    }
}
