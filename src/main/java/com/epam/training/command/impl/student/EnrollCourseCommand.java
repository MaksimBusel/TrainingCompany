package main.java.com.epam.training.command.impl.student;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.constant.CommandType;
import main.java.com.epam.training.RedirectUrlCreator;
import main.java.com.epam.training.entity.User;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.StudentCourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class EnrollCourseCommand implements Command {
    private static final String COURSE_FINISHED = "message.enrollfailed";
    private static final String COURSE_ID = "course_id";
    private static final String ENROLL_RESULT = "&result=";
    private static final String DATE_TO = "date_to";
    private static final String USER = "user";

    private StudentCourseService service;

    public EnrollCourseCommand(StudentCourseService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(USER);
        long userId = user.getId();
        String course = request.getParameter(COURSE_ID);
        long courseId = Long.parseLong(course);
        String dateTo = request.getParameter(DATE_TO);
        LocalDate localDateTo = LocalDate.parse(dateTo);
        String result = service.enroll(userId, courseId, localDateTo);
        return result.equals(COURSE_FINISHED) ?
                CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_MAIN_PAGE) + ENROLL_RESULT + result) :
                CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_MY_COURSES) + ENROLL_RESULT + result);
    }
}
