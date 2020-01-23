package com.epam.training.dao;

import com.epam.training.entity.User;
import com.epam.training.exception.DaoException;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
}
