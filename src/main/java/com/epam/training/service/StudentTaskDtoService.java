package com.epam.training.service;

import com.epam.training.dao.DaoHelper;
import com.epam.training.dao.DaoHelperFactory;
import com.epam.training.dao.impl.StudentTaskDtoDaoImpl;
import com.epam.training.dto.StudentTaskDto;
import com.epam.training.exception.DaoException;
import com.epam.training.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class StudentTaskDtoService {
    DaoHelperFactory daoHelperFactory;

    public StudentTaskDtoService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory=daoHelperFactory;
    }

    public List<StudentTaskDto> showStudentsHaveTask(String taskId) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            StudentTaskDtoDaoImpl dao = helper.createStudentTaskDtoDao();
            return dao.findStudentHaveTaskByTaskId(taskId);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }
}
