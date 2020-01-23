package com.epam.training.dao.impl;

import com.epam.training.dao.AbstractDao;
import com.epam.training.dao.UserDao;
import com.epam.training.mapper.UserRowMapper;
import com.epam.training.entity.User;
import com.epam.training.exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final String FIND_BY_LOGIN_AND_PASSWORD ="SELECT * FROM users WHERE login = ? and password = ?";
    private static final String FIND_BY_ID ="SELECT * FROM users WHERE user_id = ?";
    private static final String REMOVE_BY_ID ="DELETE FROM users WHERE user_id = ?";
    private static final String SAVE_USER = "INSERT INTO users (first_name, last_name, login, password, role) VALUES(?, ?, ?, ?, ?)";

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return User.TABLE;
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
       return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, new UserRowMapper(), login, password);
    }

    @Override
    public Optional<User> getById(Long id) throws DaoException {
        return executeForSingleResult(FIND_BY_ID, new UserRowMapper(), id);
    }

    @Override
    public void save(User user) throws DaoException {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String login = user.getLogin();
        String password = user.getPassword();
        String role = user.getRole().name();
        try(PreparedStatement statement = createStatement(SAVE_USER, firstName,lastName, login,password,role.toLowerCase())){
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void removeById(Long id) throws DaoException {
        try(PreparedStatement statement = createStatement(REMOVE_BY_ID, id)){
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
