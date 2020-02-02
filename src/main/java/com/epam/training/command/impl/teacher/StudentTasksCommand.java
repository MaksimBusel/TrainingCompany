package com.epam.training.command.impl.teacher;

import com.epam.training.command.Command;
import com.epam.training.command.CommandResult;
import com.epam.training.constant.PagesConstant;
import com.epam.training.entity.StudentTask;
import com.epam.training.exception.ServiceException;
import com.epam.training.service.StudentTaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class StudentTasksCommand implements Command {
    StudentTaskService service;

    public StudentTasksCommand(StudentTaskService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String student = request.getParameter("student_id");
        long studentId= Long.valueOf(student);
        String course = request.getParameter("course_id");
        long courseId= Long.valueOf(course);
        String studentName = request.getParameter("name");
        List<StudentTask> tasks = service.showStudentTask(studentId, courseId);
        request.setAttribute("tasks", tasks);
        request.setAttribute("student_name", studentName);

        return CommandResult.forward(PagesConstant.STUDENT_TASKS);
    }
}
