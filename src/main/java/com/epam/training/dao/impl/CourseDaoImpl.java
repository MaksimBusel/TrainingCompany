package com.epam.training.dao.impl;

import com.epam.training.dao.AbstractDao;
import com.epam.training.dao.CourseDao;
import com.epam.training.entity.Course;
import com.epam.training.entity.User;
import com.epam.training.exception.DaoException;
import com.epam.training.mapper.CourseRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CourseDaoImpl extends AbstractDao<Course> implements CourseDao {
    private static final String FIND_BY_ID ="SELECT * FROM courses WHERE course_id = ?";
    private static final String REMOVE_BY_ID ="DELETE FROM courses WHERE course_id = ?";
    private static final String SAVE_COURSE = "INSERT INTO courses (course_name, description, date_from, date_to) VALUES(?, ?, ?, ?)";
    public static final String FIND_COURSES_WITH_TEACHERS = "SELECT courses.*, first_name, last_name FROM courses inner join users on teacher_id=user_id";

    public CourseDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return User.TABLE;
    }

    @Override
    public Optional<Course> getById(Long id) throws DaoException {
        return executeForSingleResult(FIND_BY_ID, new CourseRowMapper(), id);
    }

    @Override
    public void save(Course course) throws DaoException {
        String courseName = course.getName();
        String description = course.getDescription();
        String dateFrom = course.getDateFrom();
        String dateTo = course.getDateTo();
        try(PreparedStatement statement = createStatement(SAVE_COURSE, courseName,description, dateFrom,dateTo)){
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

    public List<Course> getAll() throws DaoException{
        return executeQuery(FIND_COURSES_WITH_TEACHERS, new CourseRowMapper());
    }
}
