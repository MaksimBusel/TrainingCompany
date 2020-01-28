package com.epam.training.service;

import com.epam.training.dao.DaoHelper;
import com.epam.training.dao.DaoHelperFactory;
import com.epam.training.dao.impl.StudentTaskDaoImpl;
import com.epam.training.entity.StudentTask;
import com.epam.training.exception.DaoException;
import com.epam.training.exception.ServiceException;

import javax.servlet.http.Part;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

    public void estimateTask(String taskId, String mark, String feedback) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            StudentTaskDaoImpl dao = helper.createStudentTaskDao();
            dao.updateMarkAndFeedbackById(taskId, mark, feedback);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public List<StudentTask> showStudentTask(String studentId, String courseId) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            StudentTaskDaoImpl dao = helper.createStudentTaskDao();
            return dao.findStudentTask(studentId, courseId);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void uploadStudentTask(Part studentTask, String taskId, long userId) throws ServiceException, IOException {
        try(DaoHelper helper = daoHelperFactory.create()){

            System.out.println("начало сервиса");

            StudentTaskDaoImpl dao = helper.createStudentTaskDao();
            if(studentTask!=null){
                System.out.println(studentTask.getName());
            }
            InputStream inputStream = studentTask.getInputStream();
            System.out.println(inputStream);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

//    private String saveFileOn(Part studentTask) throws IOException {
//        InputStream inputStream = studentTask.getInputStream();
//        String pathToFile = "/src/main/students/tasks/" + extractFileName(studentTask);
//        FileOutputStream outputStream = new FileOutputStream(pathToFile);
//
//        int time = 0;
//        while ((time=inputStream.read())!=-1){
//
//        }
//    }

//    private String extractFileName(Part studentTask){
//        String contentDisposition = studentTask.getHeader("content-disposition");
//        String[] items = contentDisposition.split(";");
//        for (String time : items) {
//            System.out.println(time);
//            if (time.trim().startsWith("filename")) {
//                String clientFileName = time.substring(time.indexOf("=") + 2, time.length() - 1);
//                clientFileName = clientFileName.replace("\\", "/");
//                int i = clientFileName.lastIndexOf('/');
//                return clientFileName.substring(i + 1);
//            }
//        }
//    }
}
