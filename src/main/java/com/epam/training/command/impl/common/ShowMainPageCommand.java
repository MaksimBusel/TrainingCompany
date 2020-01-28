package com.epam.training.command.impl.common;

import com.epam.training.command.Command;
import com.epam.training.command.CommandResult;
import com.epam.training.constant.PagesConstant;
import com.epam.training.dto.CourseDto;
import com.epam.training.entity.Course;
import com.epam.training.exception.ServiceException;
import com.epam.training.service.CourseDtoService;
import com.epam.training.service.CoursesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowMainPageCommand implements Command {
    private CourseDtoService service;

    public ShowMainPageCommand(CourseDtoService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<CourseDto> courses = service.showCourses();
        request.setAttribute("courses", courses);

        return CommandResult.forward(PagesConstant.MAIN);
    }
}
