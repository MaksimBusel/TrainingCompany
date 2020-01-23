package com.epam.training.command.impl.common;

import com.epam.training.command.Command;
import com.epam.training.command.CommandResult;
import com.epam.training.entity.Course;
import com.epam.training.exception.ServiceException;
import com.epam.training.service.CoursesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowCoursesCommand implements Command {
    private CoursesService service;

    public ShowCoursesCommand(CoursesService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);
        List<Course> courses = service.showCourses();
        request.setAttribute("courses", courses);

        return CommandResult.redirect("/WEB-INF/jsp/mainpage.jsp");
    }
}
