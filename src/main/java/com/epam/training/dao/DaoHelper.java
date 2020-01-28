package com.epam.training.dao;

import com.epam.training.connection.ConnectionPool;
import com.epam.training.connection.ProxyConnection;
import com.epam.training.dao.impl.*;
import com.epam.training.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {
    private ProxyConnection connection;

    public DaoHelper(ConnectionPool connectionPool) {
        this.connection = connectionPool.getConnection();
    }

    public UserDaoImpl createUserDao() {
        return new UserDaoImpl(connection);
    }

    public TaskDaoImpl createTaskDao() {
        return new TaskDaoImpl(connection);
    }

    public CourseDaoImpl createCourseDao() {
        return new CourseDaoImpl(connection);
    }

    public CourseDtoDaoImpl createCourseDtoDao() {
        return new CourseDtoDaoImpl(connection);
    }

    public StudentTaskDaoImpl createStudentTaskDao() {
        return new StudentTaskDaoImpl(connection);
    }

    public StudentCourseDaoImpl createStudentCourseDao() {
        return new StudentCourseDaoImpl(connection);
    }

    public StudentTaskDtoDaoImpl createStudentTaskDtoDao() {
        return new StudentTaskDtoDaoImpl(connection);
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
