package com.epam.training.service;

import com.epam.training.dao.impl.CourseDaoImpl;
import com.epam.training.dao.DaoHelper;
import com.epam.training.dao.DaoHelperFactory;
import com.epam.training.entity.Course;
import com.epam.training.exception.DaoException;
import com.epam.training.exception.ServiceException;

import java.util.List;

public class CoursesService {
    DaoHelperFactory daoHelperFactory;

    public CoursesService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory=daoHelperFactory;
    }

    public boolean enrollCourse() throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            CourseDaoImpl dao = helper.createCourseDao();
            return true;
        }
    }

    public List<Course> showCourses() throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            CourseDaoImpl dao = helper.createCourseDao();
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
