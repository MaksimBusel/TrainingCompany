package com.epam.training.service;

import com.epam.training.dao.DaoHelper;
import com.epam.training.dao.DaoHelperFactory;
import com.epam.training.dao.impl.StudentTaskDaoImpl;
import com.epam.training.entity.StudentTask;
import com.epam.training.exception.DaoException;
import com.epam.training.exception.ServiceException;

import javax.servlet.http.Part;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentTaskService {
    DaoHelperFactory daoHelperFactory;
    private static final String SAVE_DIRECTORY = "D:/java/Training company/src/main/students/tasks/";

    public StudentTaskService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<StudentTask> showMyMarks(long userId, long courseId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            StudentTaskDaoImpl dao = helper.createStudentTaskDao();
            return dao.findMyMarks(userId, courseId);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void estimateTask(long taskId, Integer mark, String feedback) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            StudentTaskDaoImpl dao = helper.createStudentTaskDao();
            if (mark >= 0 && mark <= 10) {
                dao.updateMarkAndFeedbackById(taskId, mark, feedback);
            } else {
                throw new IllegalArgumentException(); //заменить на свое исключение
            }
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public List<StudentTask> showStudentTask(long studentId, long courseId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            StudentTaskDaoImpl dao = helper.createStudentTaskDao();
            return dao.findStudentTask(studentId, courseId);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public String downloadStudentTask(long studentTaskId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            StudentTaskDaoImpl dao = helper.createStudentTaskDao();
            Optional<StudentTask> studentTask = dao.findById(studentTaskId);
            if (studentTask.isPresent()) {
                String filePath = studentTask.get().getFilePath();
                return filePath;
            }
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    public void uploadStudentTask(Part studentTask, long taskId, long userId, long courseId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            StudentTaskDaoImpl dao = helper.createStudentTaskDao();
            String pathToFile = saveFile(studentTask);
            dao.addStudentTask(pathToFile, taskId, userId, courseId);
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    private String saveFile(Part studentTask) {
        String fileName = getFileName(studentTask);
        String pathToFile = SAVE_DIRECTORY + fileName;
        try (InputStream fileContent = studentTask.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(new File(pathToFile))) {
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = fileContent.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();//логировать
        }
        return pathToFile;
    }

    private String getFileName(Part studentTask) {
        final String partHeader = studentTask.getHeader("content-disposition");
        for (String content : studentTask.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    public void add(long courseId) {

    }
}
