package com.epam.training.command.impl.student;

import com.epam.training.RedirectUrlCreator;
import com.epam.training.command.Command;
import com.epam.training.command.CommandResult;
import com.epam.training.command.CommandType;
import com.epam.training.constant.PagesConstant;
import com.epam.training.entity.User;
import com.epam.training.exception.ServiceException;
import com.epam.training.service.StudentTaskService;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

public class UploadStudentTaskCommand implements Command {
    StudentTaskService service;

    public UploadStudentTaskCommand(StudentTaskService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            System.out.println("начало команды");

            Part studentTask = request.getPart("student_task");
            String taskId = request.getParameter("task_id");
            HttpSession session = request.getSession();
            User student = (User) session.getAttribute("user");
            long userId = student.getId();
            service.uploadStudentTask(studentTask, taskId, userId);
        } catch (IOException | ServletException e) {
            throw new ServiceException(e);
        }

        System.out.println("конец команды");

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_MAIN_PAGE));
    }
}
