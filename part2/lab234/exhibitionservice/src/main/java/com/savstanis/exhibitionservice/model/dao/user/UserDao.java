package com.savstanis.exhibitionservice.model.dao.user;

import com.savstanis.exhibitionservice.model.entity.User;

import java.util.Optional;

public interface UserDao {
    void create(User user);
    Optional<User> findById(int id);
    Optional<User> findByEmail(String email);
    void close();
}
