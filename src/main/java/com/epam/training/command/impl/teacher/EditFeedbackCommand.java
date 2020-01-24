package com.epam.training.command.impl.teacher;

import com.epam.training.command.Command;
import com.epam.training.command.CommandResult;
import com.epam.training.command.CommandType;
import com.epam.training.command.RedirectUrlCreator;
import com.epam.training.exception.ServiceException;
import com.epam.training.service.StudentTaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditFeedbackCommand implements Command {
    private static final String SHOW_MY_MARKS_COMMAND = "command=showMyMarks";
    private StudentTaskService service;

    public EditFeedbackCommand(StudentTaskService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String feedback = request.getParameter("feedback");
        String taskId =  request.getParameter("task_id");
        service.editFeedback(taskId, feedback);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_MY_MARKS));
    }
}
