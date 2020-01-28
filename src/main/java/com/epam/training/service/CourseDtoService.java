package com.epam.training.service;

import com.epam.training.dao.DaoHelper;
import com.epam.training.dao.DaoHelperFactory;
import com.epam.training.dao.impl.CourseDtoDaoImpl;
import com.epam.training.dto.CourseDto;
import com.epam.training.exception.DaoException;
import com.epam.training.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class CourseDtoService {
    DaoHelperFactory daoHelperFactory;

    public CourseDtoService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory=daoHelperFactory;
    }

    public List<CourseDto> showCourses() throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            CourseDtoDaoImpl dao = helper.createCourseDtoDao();
            return dao.findCoursesWithTeachersNames();
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }
}
