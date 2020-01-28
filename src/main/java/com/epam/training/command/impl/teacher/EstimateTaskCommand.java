package com.epam.training.command.impl.teacher;

import com.epam.training.command.Command;
import com.epam.training.command.CommandResult;
import com.epam.training.command.CommandType;
import com.epam.training.RedirectUrlCreator;
import com.epam.training.exception.ServiceException;
import com.epam.training.service.StudentTaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EstimateTaskCommand implements Command {
    private static final String TASK_ID_PARAMETER = "&task_id=";
    private StudentTaskService service;

    public EstimateTaskCommand(StudentTaskService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String mark = request.getParameter("mark");
        String feedback = request.getParameter("feedback");
        String studentTaskId =  request.getParameter("student_task_id");
        service.estimateTask(studentTaskId, mark, feedback);
        String taskId =  request.getParameter("task_id");

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_STUDENTS_HAVE_TASK)+TASK_ID_PARAMETER+taskId);
    }
}
