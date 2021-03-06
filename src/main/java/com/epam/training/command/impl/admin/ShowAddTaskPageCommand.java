package main.java.com.epam.training.command.impl.admin;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.constant.Pages;
import main.java.com.epam.training.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAddTaskPageCommand implements Command {
    private static final String COURSE_ID = "course_id";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String course = request.getParameter(COURSE_ID);
        long courseId= Long.parseLong(course);
        request.setAttribute("courseId", courseId);

        return CommandResult.forward(Pages.ADD_TASK);
    }
}
