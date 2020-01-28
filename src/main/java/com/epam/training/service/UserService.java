package com.epam.training.service;

import com.epam.training.dao.DaoHelper;
import com.epam.training.dao.DaoHelperFactory;
import com.epam.training.dao.UserDao;
import com.epam.training.dao.impl.UserDaoImpl;
import com.epam.training.entity.User;
import com.epam.training.exception.DaoException;
import com.epam.training.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private DaoHelperFactory daoHelperFactory;

    public UserService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory=daoHelperFactory;
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            UserDao dao = helper.createUserDao();
            return dao.findUserByLoginAndPassword(login, password);
        } catch (DaoException | SQLException e){
            throw new ServiceException(e);
        }
    }


    public List<User> showCourseStudents(String courseId) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            UserDaoImpl dao = helper.createUserDao();
            return dao.findCourseStudents(courseId);
        } catch (SQLException | DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<User> showTeachers() throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            UserDaoImpl dao = helper.createUserDao();
            return dao.getAllTeachers();
        } catch (SQLException | DaoException e){
            throw new ServiceException(e);
        }

    }
}
