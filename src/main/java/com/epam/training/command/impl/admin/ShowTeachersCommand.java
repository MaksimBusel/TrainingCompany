package com.epam.training.command.impl.admin;

import com.epam.training.command.Command;
import com.epam.training.command.CommandResult;
import com.epam.training.exception.ServiceException;
import com.epam.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowTeachersCommand implements Command {
    private UserService service;

    public ShowTeachersCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return null;
    }
}
