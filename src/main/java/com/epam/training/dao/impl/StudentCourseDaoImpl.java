package com.epam.training.dao.impl;

import com.epam.training.dao.AbstractDao;
import com.epam.training.dao.StudentCourseDao;
import com.epam.training.dto.CourseDto;
import com.epam.training.entity.Course;
import com.epam.training.entity.Identifable;
import com.epam.training.exception.DaoException;
import com.epam.training.mapper.CourseDtoRowMapper;
import com.epam.training.mapper.CourseRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class StudentCourseDaoImpl extends AbstractDao implements StudentCourseDao {
    private static final String TABLE = "users_courses";

    private static final String WHERE_COURSES_ID_EQUALS_STUDENT_ID = " WHERE course_id IN (SELECT course_id " +
            "FROM users_courses WHERE user_id=?)";
    private static final String ENROLL_STUDENT_IN_COURSE = "INSERT INTO users_courses (user_id, course_id) VALUES(?,?)";
    private static final String REMOVE_STUDENT_FROM_COURSE = "DELETE FROM users_courses WHERE user_id=? AND course_id=?";

    public StudentCourseDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TABLE;
    }

    @Override
    public Optional findById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void save(Identifable item) throws DaoException {

    }

    @Override
    public void removeById(Long id) throws DaoException {

    }

    public List<CourseDto> getCoursesById(Long id) throws DaoException {
       String coursesInfo= CourseDtoDaoImpl.FIND_COURSES_WITH_TEACHERS;
       return executeQuery(coursesInfo + WHERE_COURSES_ID_EQUALS_STUDENT_ID, new CourseDtoRowMapper(), id);
    }
    
    public void enrollStudentInCourse(long userId, long courseId) throws DaoException {
        executeUpdate(ENROLL_STUDENT_IN_COURSE, userId, courseId);
    }

    public void unenrollStudentFromCourse(long userId, long courseId) throws DaoException {
        executeUpdate(REMOVE_STUDENT_FROM_COURSE, userId, courseId);
    }
}
