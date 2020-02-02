package com.epam.training.command.impl.common;

import com.epam.training.command.Command;
import com.epam.training.command.CommandResult;
import com.epam.training.constant.PagesConstant;
import com.epam.training.entity.StudentTask;
import com.epam.training.entity.Task;
import com.epam.training.exception.ServiceException;
import com.epam.training.service.StudentTaskService;
import com.epam.training.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowCourseTasksCommand implements Command {
    private TaskService service;
    private StudentTaskService studentTaskService;

    public ShowCourseTasksCommand(TaskService service) {
        this.service = service;
     //   this.studentTaskService = studentTaskService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String course = request.getParameter("course_id");
        long courseId= Long.valueOf(course);
        List<Task> tasks = service.showTasksCourse(courseId);
        request.setAttribute("tasks",tasks);
        request.setAttribute("courseId", courseId);

        return CommandResult.forward(PagesConstant.COURSE_TASKS);
    }
}
