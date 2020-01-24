package com.epam.training.command.impl.admin;

import com.epam.training.command.Command;
import com.epam.training.command.CommandResult;
import com.epam.training.command.CommandType;
import com.epam.training.command.RedirectUrlCreator;
import com.epam.training.exception.ServiceException;
import com.epam.training.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditTaskCommand implements Command {
    private TaskService service;

    public EditTaskCommand(TaskService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String taskId = request.getParameter("task_id");
        String courseId = request.getParameter("course_id");
        String name = request.getParameter("name");
        String dateFrom = request.getParameter("date_from");
        String dateTo = request.getParameter("date_to");
        boolean result = service.editTask(courseId, name, dateFrom, dateTo, taskId);
        request.setAttribute("edit", result);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_COURSE_TASKS));
    }
}
