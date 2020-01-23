package com.epam.training.mapper;

import com.epam.training.entity.User;
import com.epam.training.entity.UserRole;


import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User>{

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(User.ID);
        String firstName = resultSet.getString(User.FIRST_NAME);
        String lastName = resultSet.getString(User.LAST_NAME);
        String login = resultSet.getString(User.LOGIN);
        String password = resultSet.getString(User.PASSWORD);
        String role = resultSet.getString(User.ROLE);
        UserRole userRole = UserRole.valueOf(role.toUpperCase());
        User user = new User(id, firstName, lastName, login, password, userRole);

        return user;
    }
}
