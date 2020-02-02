package com.epam.training.command.impl.teacher;

import com.epam.training.command.Command;
import com.epam.training.command.CommandResult;
import com.epam.training.constant.PagesConstant;
import com.epam.training.dto.StudentTaskDto;
import com.epam.training.exception.ServiceException;
import com.epam.training.service.StudentTaskDtoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class StudentsHaveTask implements Command {
    StudentTaskDtoService service;

    public StudentsHaveTask(StudentTaskDtoService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String task = request.getParameter("task_id");
        long taskId= Long.valueOf(task);
        List<StudentTaskDto> students = service.showStudentsHaveTask(taskId);
        String taskName = request.getParameter("task_name");
        request.setAttribute("students", students);
        request.setAttribute("taskName",taskName);

        return CommandResult.forward(PagesConstant.STUDENTS_HAVE_TASKS);
    }
}
