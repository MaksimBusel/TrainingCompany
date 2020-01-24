package com.epam.training.command;

import com.epam.training.command.impl.admin.*;
import com.epam.training.command.impl.common.*;
import com.epam.training.command.impl.student.EnrollCourseCommand;
import com.epam.training.command.impl.student.ShowMyCoursesCommand;
import com.epam.training.command.impl.student.ShowMyMarksCommand;
import com.epam.training.command.impl.student.UnenrollCourseCommand;
import com.epam.training.command.impl.teacher.EditFeedbackCommand;
import com.epam.training.command.impl.teacher.EditMarkCommand;
import com.epam.training.dao.DaoHelperFactory;
import com.epam.training.service.*;

public class CommandFactory {

    public Command findCommand(String command) {
        System.out.println(command);
        switch (command) {
            case CommandType.LOGIN:
                return new LoginCommand(new UserService(new DaoHelperFactory()));
            case CommandType.SHOW_MAIN_PAGE:
                return new ShowCoursesCommand(new CoursesService(new DaoHelperFactory()));
            case CommandType.SHOW_COURSES:
                return new ShowCoursesCommand(new CoursesService(new DaoHelperFactory())); //убрать 1 из команд
            case CommandType.LOG_OUT:
                return new LogoutCommand();
            case CommandType.SHOW_MY_COURSES:
                return new ShowMyCoursesCommand(new StudentCourseService(new DaoHelperFactory()));
            case CommandType.ENROLL_COURSE:
                return new EnrollCourseCommand(new StudentCourseService(new DaoHelperFactory()));
            case CommandType.SHOW_MY_MARKS:
                return new ShowMyMarksCommand(new StudentTaskService(new DaoHelperFactory()));
            case CommandType.SHOW_COURSE_TASKS:
                return new ShowCourseTasksCommand(new TaskService(new DaoHelperFactory()));
            case CommandType.UNENROLL_COURSE:
                return new UnenrollCourseCommand(new StudentCourseService(new DaoHelperFactory()));

            case CommandType.EDIT_TASK:
                return new EditTaskCommand(new TaskService(new DaoHelperFactory()));
            case CommandType.EDIT_COURSE:
                return new EditCourseCommand(new CoursesService(new DaoHelperFactory()));
            case CommandType.EDIT_FEEDBACK:
                return new EditFeedbackCommand(new StudentTaskService(new DaoHelperFactory()));
            case CommandType.EDIT_MARK:
                return new EditMarkCommand(new StudentTaskService(new DaoHelperFactory()));
            case CommandType.ADD_COURSE:
                return new AddCourseCommand(new CoursesService(new DaoHelperFactory()));
            case CommandType.ADD_TASK:
                return new AddTaskCommand(new TaskService(new DaoHelperFactory()));
            case CommandType.DELETE_TASK:
                return new DeleteTaskCommand(new TaskService(new DaoHelperFactory()));

            case CommandType.ADD_STUDENT_TASK:
                return new ShowPageCommand("/WEB-INF/main.jsp");

            case CommandType.SHOW_TEACHERS:
                return new ShowPageCommand("/WEB-INF/main.jsp");
            case CommandType.SHOW_STUDENTS:
                return new ShowPageCommand("/WEB-INF/main.jsp");

            default:
                throw new IllegalArgumentException("Unknown command" + command);
        }
    }
}
