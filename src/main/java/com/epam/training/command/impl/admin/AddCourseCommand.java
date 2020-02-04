package main.java.com.epam.training.command.impl.admin;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.constant.CommandType;
import main.java.com.epam.training.RedirectUrlCreator;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.CoursesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class AddCourseCommand implements Command {
    private CoursesService service;

    public AddCourseCommand(CoursesService service) {
        this.service = service;
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String teacher = request.getParameter("teacher");
        long teacherId = Long.valueOf(teacher);
        String name = request.getParameter("course");
        String description = request.getParameter("description");
        String dateFrom = request.getParameter("dateFrom");
        LocalDate localDateFrom = LocalDate.parse(dateFrom);
        String dateTo = request.getParameter("dateTo");
        LocalDate localDateTo = LocalDate.parse(dateTo);
        service.addCourse(teacherId, name, description, localDateFrom, localDateTo);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_MAIN_PAGE));
    }
}
