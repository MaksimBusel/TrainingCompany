package com.epam.training.service;

import com.epam.training.dao.DaoHelper;
import com.epam.training.dao.DaoHelperFactory;
import com.epam.training.dao.impl.TaskDaoImpl;
import com.epam.training.entity.Task;
import com.epam.training.exception.DaoException;
import com.epam.training.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class TaskService {
    DaoHelperFactory daoHelperFactory;

    public TaskService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory=daoHelperFactory;
    }

    public int addTask(long courseId, String name, String dateFrom, String dateTo) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            TaskDaoImpl dao = helper.createTaskDao();
            int result = dao.save(courseId, name, dateFrom, dateTo);
            return result;
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public List<Task> showTasksCourse(long courseId) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            TaskDaoImpl dao = helper.createTaskDao();
            return dao.findAllByCourseId(courseId);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public boolean lockTask(long taskId) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            TaskDaoImpl dao = helper.createTaskDao();
            dao.removeById(taskId);
            return true;
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public boolean editTask(long courseId, String name, String dateFrom, String dateTo, long taskId) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            TaskDaoImpl dao = helper.createTaskDao();
            dao.updateById(courseId, name, dateFrom, dateTo, taskId);
            return true;
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }
}
