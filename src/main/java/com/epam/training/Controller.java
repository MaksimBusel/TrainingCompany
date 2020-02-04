package main.java.com.epam.training;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandFactory;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
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
            dispatch(request, response, result);
        } catch (ServiceException e) {
            e.printStackTrace();//логировать
        }
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, CommandResult result) throws ServletException, IOException {
        if (result != null) {
            String page = result.getPage();
            if (result.isRedirect()) {
                response.sendRedirect(request.getContextPath() + page);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }
        }
    }
}

