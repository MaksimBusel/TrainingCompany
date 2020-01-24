package com.epam.training.service;

import com.epam.training.dao.DaoHelper;
import com.epam.training.dao.DaoHelperFactory;
import com.epam.training.dao.impl.StudentCourseDaoImpl;
import com.epam.training.entity.Course;
import com.epam.training.exception.DaoException;
import com.epam.training.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class StudentCourseService {
    DaoHelperFactory daoHelperFactory;

    public StudentCourseService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<Course> showMyCourses(long id) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            StudentCourseDaoImpl dao = helper.createStudentCourseDao();
            return dao.getCoursesById(id);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void enroll(long userId, String courseId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            StudentCourseDaoImpl dao = helper.createStudentCourseDao();
            dao.enrollStudentInCourse(userId, courseId);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void unenroll(long userId, String courseId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            StudentCourseDaoImpl dao = helper.createStudentCourseDao();
            dao.unenrollStudentFromCourse(userId, courseId);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }
}