package com.epam.training.service;

import com.epam.training.dao.impl.CourseDaoImpl;
import com.epam.training.dao.DaoHelper;
import com.epam.training.dao.DaoHelperFactory;
import com.epam.training.entity.Course;
import com.epam.training.exception.DaoException;
import com.epam.training.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class CoursesService {
    DaoHelperFactory daoHelperFactory;

    public CoursesService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory=daoHelperFactory;
    }

    public void enrollCourse() throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            CourseDaoImpl dao = helper.createCourseDao();
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public List<Course> showCourses() throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            CourseDaoImpl dao = helper.createCourseDao();
            return dao.getAll();
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void editCourse(String courseId, String teacherId, String courseName, String description, String dateFrom, String dateTo) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            CourseDaoImpl dao = helper.createCourseDao();
            dao.updateCourseById(teacherId, courseName, description, dateFrom, dateTo, courseId);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void addCourse(String teacherId, String name, String description, String dateFrom, String dateTo) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            CourseDaoImpl dao = helper.createCourseDao();
            dao.save(teacherId, name, description, dateFrom, dateTo);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }
}
