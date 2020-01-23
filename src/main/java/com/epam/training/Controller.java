package com.epam.training;

import com.epam.training.command.Command;
import com.epam.training.command.CommandResult;
import com.epam.training.exception.ServiceException;
import com.epam.training.command.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        CommandFactory factory = new CommandFactory();
        Command concreteCommand = factory.findCommand(command);
        try {
            CommandResult result = concreteCommand.execute(request, response);
            dispatch(request,response,result);
        } catch (ServiceException e) {
            e.printStackTrace();//бросать ексепшн
        }
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, CommandResult result) throws ServletException, IOException {
        String page = result.getPage();
        if (result.isRedirect()) {
            response.sendRedirect(request.getContextPath() + page);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }
}

