package main.java.com.epam.training.dao.api;

import main.java.com.epam.training.dto.CourseDto;
import main.java.com.epam.training.exception.DaoException;

import java.util.List;

public interface StudentCourseDao extends Dao<CourseDto> {

    List<CourseDto> findCoursesByUserId(long id) throws DaoException;

    void enrollStudentInCourse(long userId, long courseId) throws DaoException;

    boolean findStudentEnrolledInCourse(long studentId, long courseId) throws DaoException;

    void unenrollStudentFromCourse(long userId, long courseId) throws DaoException;
}
