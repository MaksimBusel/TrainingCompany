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

    public List<Task> showTasksCourse(String courseId) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            TaskDaoImpl dao = helper.createTaskDao();
            return dao.getTasksByCourseId(courseId);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void addTask(String courseId, String name, String dateFrom, String dateTo) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            TaskDaoImpl dao = helper.createTaskDao();
            dao.save(courseId, name, dateFrom, dateTo);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public boolean lockTask(String id) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            TaskDaoImpl dao = helper.createTaskDao();
            Long taskId = Long.valueOf(id);
            dao.removeById(taskId);
            return true;
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public boolean editTask(String courseId, String name, String dateFrom, String dateTo, String taskId) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            TaskDaoImpl dao = helper.createTaskDao();
            dao.updateById(courseId, name, dateFrom, dateTo, taskId);
            return true;
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }
}
