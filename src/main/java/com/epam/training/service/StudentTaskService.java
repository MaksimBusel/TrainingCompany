package com.epam.training.service;

import com.epam.training.dao.DaoHelper;
import com.epam.training.dao.DaoHelperFactory;
import com.epam.training.dao.impl.StudentTaskDaoImpl;
import com.epam.training.entity.StudentTask;
import com.epam.training.exception.DaoException;
import com.epam.training.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class StudentTaskService {
    DaoHelperFactory daoHelperFactory;

    public StudentTaskService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory=daoHelperFactory;
    }

    public List<StudentTask> showMyMarks(long userId, String courseId) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            StudentTaskDaoImpl dao = helper.createStudentTaskDao();
            return dao.getMyMarks(userId, courseId);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void editFeedback(String taskId, String feedback) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            StudentTaskDaoImpl dao = helper.createStudentTaskDao();
            dao.updateFeedbackById(taskId, feedback);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void editMark(String taskId, String mark) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            StudentTaskDaoImpl dao = helper.createStudentTaskDao();
            dao.updateMarkById(taskId, mark);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }
}
