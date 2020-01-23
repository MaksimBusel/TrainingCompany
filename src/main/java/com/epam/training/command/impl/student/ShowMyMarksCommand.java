package com.epam.training.command.impl.student;

import com.epam.training.command.Command;
import com.epam.training.command.CommandResult;
import com.epam.training.entity.StudentTask;
import com.epam.training.entity.User;
import com.epam.training.exception.ServiceException;
import com.epam.training.service.StudentTaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowMyMarksCommand implements Command {
    private StudentTaskService service;

    public ShowMyMarksCommand(StudentTaskService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);
        User user =(User) session.getAttribute("user");
        long userId = user.getId();
        String courseId = request.getParameter("courseId");
        List<StudentTask> tasks = service.showMyMarks(userId, courseId);
        request.setAttribute("tasks", tasks);

        return CommandResult.forward("/WEB-INF/jsp/mymarks.jsp");
    }
}
