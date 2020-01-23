package com.epam.training.command.impl.student;

import com.epam.training.command.Command;
import com.epam.training.command.CommandResult;
import com.epam.training.entity.Course;
import com.epam.training.entity.User;
import com.epam.training.exception.ServiceException;
import com.epam.training.service.StudentCourseService;
import com.epam.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowMyCoursesCommand implements Command {
    private StudentCourseService service;

    public ShowMyCoursesCommand(StudentCourseService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);
        User user =(User) session.getAttribute("user");
        long userId = user.getId();
        List<Course> myCourses = service.showMyCourses(userId);
        request.setAttribute("myCourses", myCourses);

        return CommandResult.forward("/WEB-INF/jsp/mycourses.jsp");
    }
}
