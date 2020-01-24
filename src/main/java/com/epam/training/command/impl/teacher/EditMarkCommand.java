package com.epam.training.command.impl.teacher;

import com.epam.training.command.Command;
import com.epam.training.command.CommandResult;
import com.epam.training.command.CommandType;
import com.epam.training.command.RedirectUrlCreator;
import com.epam.training.exception.ServiceException;
import com.epam.training.service.StudentTaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditMarkCommand implements Command {
    private StudentTaskService service;

    public EditMarkCommand(StudentTaskService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String mark = request.getParameter("mark");
        String taskId =  request.getParameter("task_id");
        service.editMark(taskId, mark);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_MY_MARKS));
    }
}
