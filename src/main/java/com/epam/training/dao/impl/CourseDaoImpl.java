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
    public static final String FIND_COURSES_WITH_TEACHERS = "SELECT courses.*, first_name, last_name FROM courses " +
            "inner join users on teacher_id=user_id";
    private static final String EDIT_COURSE_BY_ID = "UPDATE courses SET teacher_id=?, course_name=?, description=?, " +
            "date_from=?, date_to=? WHERE course_id =?";
    private static final String ADD_COURSE = "INSERT INTO courses (teacher_id, course_name, description, date_from, date_to)" +
            " VALUES(?, ?, ?, ?, ?)";


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
    public void removeById(Long id) throws DaoException {
        try(PreparedStatement statement = createStatement(REMOVE_BY_ID, id)){
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void save(String teacherId, String name, String description, String dateFrom, String dateTo) throws DaoException {
        executeUpdate(ADD_COURSE, teacherId, name, description, dateFrom, dateTo);
    }

    public List<Course> getAll() throws DaoException{
        return executeQuery(FIND_COURSES_WITH_TEACHERS, new CourseRowMapper());
    }

    public void updateCourseById(String teacherId, String courseName, String description, String dateFrom, String dateTo, String courseId) throws DaoException {
        executeUpdate(EDIT_COURSE_BY_ID, teacherId, courseName, description, dateFrom, dateTo, courseId);
    }
}
