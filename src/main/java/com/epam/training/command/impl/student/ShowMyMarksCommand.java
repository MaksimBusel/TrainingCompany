package main.java.com.epam.training.command.impl.student;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.constant.Pages;
import main.java.com.epam.training.entity.StudentTask;
import main.java.com.epam.training.entity.User;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.StudentTaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowMyMarksCommand implements Command {
    private static final String COURSE_ID = "courseId";
    private static final String USER = "user";

    private StudentTaskService service;

    public ShowMyMarksCommand(StudentTaskService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);
        User user =(User) session.getAttribute(USER);
        long userId = user.getId();
        String course = request.getParameter(COURSE_ID);
        long courseId= Long.parseLong(course);
        List<StudentTask> tasks = service.showMyMarks(userId, courseId);
        String uploadResult = request.getParameter("result");
        request.setAttribute("tasks", tasks);
        request.setAttribute("courseId", courseId);
        request.setAttribute("result", uploadResult);

        return CommandResult.forward(Pages.MY_MARKS);
    }
}
