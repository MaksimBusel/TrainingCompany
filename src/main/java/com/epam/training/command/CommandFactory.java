package com.epam.training.command;

import com.epam.training.command.impl.common.*;
import com.epam.training.command.impl.student.EnrollCourseCommand;
import com.epam.training.command.impl.student.ShowMyCoursesCommand;
import com.epam.training.command.impl.student.ShowMyMarksCommand;
import com.epam.training.command.impl.student.UnenrollCourseCommand;
import com.epam.training.dao.DaoHelperFactory;
import com.epam.training.service.*;

public class CommandFactory {

    public Command findCommand(String command) {
        System.out.println(command);
        switch (command) {
            case CommandType.LOGIN:
                return new LoginCommand(new UserService(new DaoHelperFactory()));
            case CommandType.LOG_OUT:
                return new LogoutCommand();
            case CommandType.SHOW_COURSES:
                return new ShowCoursesCommand(new CoursesService(new DaoHelperFactory()));
            case CommandType.MY_COURSES:
                return new ShowMyCoursesCommand(new StudentCourseService(new DaoHelperFactory()));
            case CommandType.ENROLL_COURSE:
                return new EnrollCourseCommand(new StudentCourseService(new DaoHelperFactory()));
            case CommandType.UNENROLL_COURSE:
                return new UnenrollCourseCommand(new StudentCourseService(new DaoHelperFactory()));
            case CommandType.SHOW_MY_MARKS:
                return new ShowMyMarksCommand(new StudentTaskService(new DaoHelperFactory()));
            case CommandType.SHOW_COURSE_TASKS:
                return new ShowCourseTasksCommand(new TaskService(new DaoHelperFactory()));

            case CommandType.ADD_STUDENT_TASK:
                return new ShowPageCommand("/WEB-INF/main.jsp");
            case CommandType.DELETE_FEEDBACK:
                return new ShowPageCommand("/WEB-INF/main.jsp");
            case CommandType.DELETE_MARK:
                return new ShowPageCommand("/WEB-INF/main.jsp");
            case CommandType.EDIT_FEEDBACK:
                return new ShowPageCommand("/WEB-INF/main.jsp");
            case CommandType.EDIT_MARK:
                return new ShowPageCommand("/WEB-INF/main.jsp");
            case CommandType.LEAVE_FEEDBACK:
                return new ShowPageCommand("/WEB-INF/main.jsp");
            case CommandType.RATE_MARK:
                return new ShowPageCommand("/WEB-INF/main.jsp");
            case CommandType.ADD_COURSE:
                return new ShowPageCommand("/WEB-INF/main.jsp");
            case CommandType.ADD_TASK:
                return new ShowPageCommand("/WEB-INF/main.jsp");
            case CommandType.ADD_TEACHER:
                return new ShowPageCommand("/WEB-INF/main.jsp");
            case CommandType.EDIT_COURSE:
                return new ShowPageCommand("/WEB-INF/main.jsp");
            case CommandType.EDIT_COURSE_TEACHER:
                return new ShowPageCommand("/WEB-INF/main.jsp");
            case CommandType.SHOW_STUDENTS:
                return new ShowPageCommand("/WEB-INF/main.jsp");
            case CommandType.SHOW_TEACHERS:
                return new ShowPageCommand("/WEB-INF/main.jsp");

            case CommandType.MAIN_PAGE:
                return new ShowCoursesCommand(new CoursesService(new DaoHelperFactory()));

            default:
                throw new IllegalArgumentException("Unknown command" + command);
        }
    }
}
