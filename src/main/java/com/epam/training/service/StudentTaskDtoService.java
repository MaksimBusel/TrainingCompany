package main.java.com.epam.training.service;

import main.java.com.epam.training.dao.DaoHelper;
import main.java.com.epam.training.dao.DaoHelperFactory;
import main.java.com.epam.training.dao.api.StudentTaskDtoDao;
import main.java.com.epam.training.dto.StudentTaskDto;
import main.java.com.epam.training.exception.DaoException;
import main.java.com.epam.training.exception.ServiceException;

import java.util.List;

public class StudentTaskDtoService {
    private DaoHelperFactory daoHelperFactory;

    public StudentTaskDtoService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory=daoHelperFactory;
    }

    public List<StudentTaskDto> showStudentsHaveTask(long taskId) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            StudentTaskDtoDao dao = helper.createStudentTaskDtoDao();
            return dao.findStudentHaveTaskByTaskId(taskId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
