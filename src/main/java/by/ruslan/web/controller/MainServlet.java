package by.ruslan.web.controller;

import by.ruslan.web.command.Command;
import by.ruslan.web.command.CommandFactory;
import by.ruslan.web.command.PagePath;
import by.ruslan.web.command.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "MainServlet", value = "/main-servlet")
public class MainServlet extends HttpServlet {

    static final Logger logger = LogManager.getLogger();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Command> optionalCommand = CommandFactory.defineCommand(request);
        if (optionalCommand.isPresent()){
            Command command = optionalCommand.get();
            Router router = command.execute(request);
            String page = router.getPath();
            switch (router.getType()){
                case FORWARD -> {
                    request.getRequestDispatcher(page).forward(request, response);
                }
                case REDIRECT -> {
                    response.sendRedirect(page);
                }
            }
        }else {
            response.sendError(404);
        }
    }
}