package com.epam.training.dao.impl;

import com.epam.training.dao.AbstractDao;
import com.epam.training.dao.CourseDao;
import com.epam.training.entity.Course;
import com.epam.training.exception.DaoException;
import com.epam.training.mapper.CourseRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CourseDaoImpl extends AbstractDao<Course> implements CourseDao {
    private static final String FIND_BY_ID ="SELECT * FROM courses WHERE course_id = ? AND lock_course=0";
    private static final String LOCK_BY_ID ="UPDATE courses SET lock_course=1 WHERE course_id = ?";
    private static final String FIND_TEACHER_COURSES ="SELECT * FROM courses WHERE teacher_id=? AND lock_course=0";
    private static final String EDIT_COURSE_BY_ID = "UPDATE courses SET teacher_id=?, course_name=?, description=?, " +
            "date_from=?, date_to=? WHERE course_id =?";
    private static final String ADD_COURSE = "INSERT INTO courses (teacher_id, course_name, description, date_from, date_to)" +
            " VALUES(?, ?, ?, ?, ?)";


    public CourseDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return Course.TABLE;
    }

    @Override
    public Optional<Course> findById(Long id) throws DaoException {
        return executeForSingleResult(FIND_BY_ID, new CourseRowMapper(), id);
    }

    @Override
    public void save(Course item) throws DaoException {

    }

    @Override
    public void removeById(Long id) throws DaoException {
       executeUpdate(LOCK_BY_ID, id);
    }

    public void save(String teacherId, String name, String description, String dateFrom, String dateTo) throws DaoException {
        executeUpdate(ADD_COURSE, teacherId, name, description, dateFrom, dateTo);
    }

    public void updateCourseById(String teacherId, String courseName, String description, String dateFrom, String dateTo, String courseId) throws DaoException {
        executeUpdate(EDIT_COURSE_BY_ID, teacherId, courseName, description, dateFrom, dateTo, courseId);
    }

    public List<Course> findTeacherCourses(long teacherId) throws DaoException {
        return executeQuery(FIND_TEACHER_COURSES, new CourseRowMapper(), teacherId);
    }
}
