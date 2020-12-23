package com.savstanis.exhibitionservice.model.dao.user;

import com.savstanis.exhibitionservice.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    public User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        return User.builder()
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("name"))
                .setEmail(resultSet.getString("email"))
                .setPassword(resultSet.getString("password"))
                .setRole(resultSet.getString("role"))
                .build();
    }
}
