package main.java.com.epam.training.service;

import main.java.com.epam.training.dao.api.CourseDtoDao;
import main.java.com.epam.training.dao.DaoHelper;
import main.java.com.epam.training.dao.DaoHelperFactory;
import main.java.com.epam.training.dto.CourseDto;
import main.java.com.epam.training.exception.DaoException;
import main.java.com.epam.training.exception.ServiceException;

import java.util.List;

public class CourseDtoService {
    private DaoHelperFactory daoHelperFactory;

    public CourseDtoService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory=daoHelperFactory;
    }

    public List<CourseDto> showCourses() throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            CourseDtoDao dao = helper.createCourseDtoDao();
            return dao.findCoursesWithTeachersNames();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
