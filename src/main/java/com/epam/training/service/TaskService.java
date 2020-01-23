package com.epam.training.service;

import com.epam.training.dao.DaoHelper;
import com.epam.training.dao.DaoHelperFactory;
import com.epam.training.dao.impl.TaskDaoImpl;
import com.epam.training.entity.Task;
import com.epam.training.exception.DaoException;
import com.epam.training.exception.ServiceException;

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
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
