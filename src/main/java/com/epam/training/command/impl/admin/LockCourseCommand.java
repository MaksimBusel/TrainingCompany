package com.epam.training.command.impl.admin;

import com.epam.training.command.Command;
import com.epam.training.command.CommandResult;
import com.epam.training.command.CommandType;
import com.epam.training.RedirectUrlCreator;
import com.epam.training.exception.ServiceException;
import com.epam.training.service.CoursesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LockCourseCommand implements Command {
    private CoursesService service;

    public LockCourseCommand(CoursesService courseService) {
        this.service = courseService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String course = request.getParameter("course_id");
        long courseId = Long.valueOf(course);
        service.lockCourse(courseId);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_EDIT_COURSE_PAGE));
    }
}
