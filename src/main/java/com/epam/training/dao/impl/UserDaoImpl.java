package com.epam.training.dao.impl;

import com.epam.training.dao.AbstractDao;
import com.epam.training.dao.UserDao;
import com.epam.training.mapper.UserRowMapper;
import com.epam.training.entity.User;
import com.epam.training.exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final String FIND_BY_LOGIN_AND_PASSWORD ="SELECT * FROM users WHERE login = ? AND password = ?";
    private static final String FIND_BY_ID ="SELECT * FROM users WHERE user_id = ?";
    private static final String REMOVE_BY_ID ="DELETE FROM users WHERE user_id = ?";
    private static final String FIND_TEACHERS ="SELECT * FROM users WHERE role='teacher'";
    private static final String FIND_COURSE_STUDENTS = "SELECT * FROM users JOIN users_courses " +
            "ON users.user_id=users_courses.user_id WHERE course_id=?";

    //not use
    private static final String SAVE_USER = "INSERT INTO users (first_name, last_name, login, password, role) VALUES(?, ?, ?, ?, ?)";
    private static final String FIND_STUDENTS_AND_INFO_ABOUT_COURSES = "SELECT first_name, last_name, login, course_name," +
            " date_from, date_to FROM users INNER JOIN users_courses ON users.user_id= users_courses.user_id INNER JOIN " +
            "courses ON courses.course_id=users_courses.course_id  WHERE lock_course=0";

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
    public Optional<User> findById(Long id) throws DaoException {
        return executeForSingleResult(FIND_BY_ID, new UserRowMapper(), id);
    }

    @Override
    public void save(User item) throws DaoException {

    }

    @Override
    public void removeById(Long id) throws DaoException {
        try(PreparedStatement statement = createStatement(REMOVE_BY_ID, id)){
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<User> getStudents(String role) throws DaoException {
        return executeQuery(FIND_STUDENTS_AND_INFO_ABOUT_COURSES, new UserRowMapper(), role);
    }

    public List<User> getAllTeachers() throws DaoException {
        return executeQuery(FIND_TEACHERS, new UserRowMapper());
    }

    public List<User> findCourseStudents(long courseId) throws DaoException {
        return executeQuery(FIND_COURSE_STUDENTS, new UserRowMapper(), courseId);
    }
}
