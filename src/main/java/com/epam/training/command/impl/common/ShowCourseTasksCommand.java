package com.epam.training.command.impl.common;

import com.epam.training.command.Command;
import com.epam.training.command.CommandResult;
import com.epam.training.entity.Task;
import com.epam.training.exception.ServiceException;
import com.epam.training.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowCourseTasksCommand implements Command {
    private TaskService service;

    public ShowCourseTasksCommand(TaskService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String courseId = request.getParameter("course_id");
        List<Task> tasks = service.showTasksCourse(courseId);
        request.setAttribute("tasks",tasks);
        request.setAttribute("course_id", courseId);

        return CommandResult.forward("/WEB-INF/jsp/coursetasks.jsp");
    }
}
