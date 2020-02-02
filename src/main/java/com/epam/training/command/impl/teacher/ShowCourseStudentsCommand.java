package com.epam.training.command.impl.teacher;

import com.epam.training.command.Command;
import com.epam.training.command.CommandResult;
import com.epam.training.constant.PagesConstant;
import com.epam.training.entity.User;
import com.epam.training.exception.ServiceException;
import com.epam.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowCourseStudentsCommand implements Command {
    UserService service;

    public ShowCourseStudentsCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String course = request.getParameter("course_id");
        long courseId= Long.valueOf(course);
        List<User> students = service.showCourseStudents(courseId);
        request.setAttribute("students", students);
        request.setAttribute("course_id", courseId);

        return CommandResult.forward(PagesConstant.COURSE_STUDENTS);
    }
}
