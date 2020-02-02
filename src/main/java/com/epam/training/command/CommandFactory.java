package com.epam.training.command;

import com.epam.training.command.impl.admin.*;
import com.epam.training.command.impl.common.*;
import com.epam.training.command.impl.student.*;
import com.epam.training.command.impl.teacher.*;
import com.epam.training.dao.DaoHelperFactory;
import com.epam.training.service.*;

public class CommandFactory {

    public Command findCommand(String command) {
        System.out.println(command);
        switch (command) {
            case CommandType.LOGIN:
                return new LoginCommand(new UserService(new DaoHelperFactory()));
            case CommandType.SHOW_MAIN_PAGE:
                return new ShowMainPageCommand(new CourseDtoService(new DaoHelperFactory()));
            case CommandType.LOG_OUT:
                return new LogoutCommand();
            case CommandType.SHOW_MY_COURSES:
                return new ShowMyCoursesCommand(new StudentCourseService(new DaoHelperFactory()));
            case CommandType.ENROLL_COURSE:
                return new EnrollCourseCommand(new StudentCourseService(new DaoHelperFactory()));
            case CommandType.SHOW_COURSE_TASKS:
                return new ShowCourseTasksCommand(new TaskService(new DaoHelperFactory()));
            case CommandType.UNENROLL_COURSE:
                return new UnenrollCourseCommand(new StudentCourseService(new DaoHelperFactory()));
            case CommandType.SHOW_MY_MARKS:
                return new ShowMyMarksCommand(new StudentTaskService(new DaoHelperFactory()));
            case CommandType.SHOW_ADD_COURSE_PAGE:
                return new ShowAddCoursePageCommand(new UserService(new DaoHelperFactory()));
            case CommandType.SHOW_EDIT_COURSE_PAGE:
                CourseDtoService coursesService = new CourseDtoService(new DaoHelperFactory());
                UserService userService = new UserService(new DaoHelperFactory());
                return new ShowEditCoursePageCommand(coursesService, userService);
            case CommandType.ADD_COURSE:
                return new AddCourseCommand(new CoursesService(new DaoHelperFactory()));
            case CommandType.EDIT_COURSE:
                return new EditCourseCommand(new CoursesService(new DaoHelperFactory()));
            case CommandType.SHOW_MANAGE_TASKS_PAGE:
                return new ShowManageTasksPageCommand (new TaskService(new DaoHelperFactory()));
            case CommandType.SHOW_ADD_TASK_PAGE:
                return new ShowAddTaskPageCommand ();
            case CommandType.EDIT_TASK:
                return new EditTaskCommand(new TaskService(new DaoHelperFactory()));
            case CommandType.ADD_TASK:
                return new AddTaskCommand(new TaskService(new DaoHelperFactory()));
            case CommandType.SHOW_TEACHER_COURSES:
                return new TeacherCoursesCommand(new CoursesService(new DaoHelperFactory()));
            case CommandType.SHOW_TEACHER_TASKS:
                return new ShowTeacherTasksCommand(new TaskService(new DaoHelperFactory()));
            case CommandType.SHOW_STUDENTS_HAVE_TASK:
                return new StudentsHaveTask(new StudentTaskDtoService(new DaoHelperFactory()));
            case CommandType.ESTIMATE_TASK:
                return new EstimateTaskCommand(new StudentTaskService(new DaoHelperFactory()));
            case CommandType.LOCK_TASK:
                return new LockTaskCommand(new TaskService(new DaoHelperFactory()));
            case CommandType.LOCK_COURSE:
                return new LockCourseCommand(new CoursesService(new DaoHelperFactory()));
            case CommandType.SHOW_COURSE_STUDENTS:
                return new ShowCourseStudentsCommand(new UserService(new DaoHelperFactory()));
            case CommandType.SHOW_STUDENT_TASKS:
                return new StudentTasksCommand(new StudentTaskService(new DaoHelperFactory()));
            case CommandType.UPLOAD_STUDENT_TASK:
                return new UploadStudentTaskCommand(new StudentTaskService(new DaoHelperFactory()));
            case CommandType.DOWNLOAD_STUDENT_TASK:
                return new DownloadStudentTaskCommand(new StudentTaskService(new DaoHelperFactory()));

            default:
                throw new IllegalArgumentException("Unknown command" + command);
        }
    }
}
