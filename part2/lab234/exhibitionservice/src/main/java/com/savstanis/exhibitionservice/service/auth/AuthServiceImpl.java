package com.savstanis.exhibitionservice.service.auth;

import com.savstanis.exhibitionservice.model.dao.DaoFactory;
import com.savstanis.exhibitionservice.model.dao.DaoFactoryImpl;
import com.savstanis.exhibitionservice.model.dao.user.UserDao;
import com.savstanis.exhibitionservice.model.dto.LoginDto;
import com.savstanis.exhibitionservice.model.dto.RegisterDto;
import com.savstanis.exhibitionservice.model.entity.User;

import java.sql.SQLException;
import java.util.Optional;

public class AuthServiceImpl implements AuthService {

    private final DaoFactory daoFactory;

    public AuthServiceImpl() {
        this.daoFactory = new DaoFactoryImpl();
    }

    @Override
    public User login(LoginDto loginDto) throws SQLException {
        UserDao userDao = daoFactory.getUserDao();
        Optional<User> user = userDao.findByEmail(loginDto.getEmail());
        userDao.close();


        if (user.isPresent() && user.get().getPassword().equals(loginDto.getPassword())) {
            return user.get();
        }
        return null;
    }

    @Override
    public boolean register(RegisterDto registerDto) throws SQLException {
        UserDao userDao = daoFactory.getUserDao();
        Optional<User> user = userDao.findByEmail(registerDto.getEmail());
        userDao.close();

        if (user.isPresent()) {
            return false;
        }

        User newUser = registerDto.toUser();
        newUser.setRole("user");
        userDao.create(newUser);

        return true;
    }
}
