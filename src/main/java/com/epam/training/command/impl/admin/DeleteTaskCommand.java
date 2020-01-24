package com.epam.training.command.impl.admin;

import com.epam.training.command.Command;
import com.epam.training.command.CommandResult;
import com.epam.training.command.CommandType;
import com.epam.training.command.RedirectUrlCreator;
import com.epam.training.exception.ServiceException;
import com.epam.training.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTaskCommand implements Command {
    private TaskService service;

    public DeleteTaskCommand(TaskService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String taskId = request.getParameter("task_id");
        boolean result = service.deleteTask(taskId);
        request.setAttribute("remove", result);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_COURSE_TASKS));
    }
}
