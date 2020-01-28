package com.epam.training.command.impl.teacher;

import com.epam.training.command.Command;
import com.epam.training.command.CommandResult;
import com.epam.training.command.CommandType;
import com.epam.training.constant.PagesConstant;
import com.epam.training.entity.Course;
import com.epam.training.entity.User;
import com.epam.training.exception.ServiceException;
import com.epam.training.service.CoursesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class TeacherCoursesCommand implements Command {
    private CoursesService service;

    public TeacherCoursesCommand(CoursesService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);
        User teacher =(User) session.getAttribute("user");
        long teacherId = teacher.getId();
        List<Course> teacherCourses = service.showTeacherCourses(teacherId);
        request.setAttribute("teacherCourses", teacherCourses);

        return CommandResult.forward(PagesConstant.TEACHER_COURSES);
    }
}
