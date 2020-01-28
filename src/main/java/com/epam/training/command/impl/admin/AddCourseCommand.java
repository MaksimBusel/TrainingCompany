package com.epam.training.command.impl.admin;

import com.epam.training.command.Command;
import com.epam.training.command.CommandResult;
import com.epam.training.command.CommandType;
import com.epam.training.RedirectUrlCreator;
import com.epam.training.exception.ServiceException;
import com.epam.training.service.CoursesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCourseCommand implements Command {
    private CoursesService service;

    public AddCourseCommand(CoursesService service) {
        this.service = service;
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String teacherId = request.getParameter("teacher");
        String name = request.getParameter("course");
        String description = request.getParameter("description");
        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");
        service.addCourse(teacherId, name, description, dateFrom, dateTo);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_MAIN_PAGE));
    }
}
