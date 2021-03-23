package by.ruslan.web.controller;

import by.ruslan.web.command.Command;
import by.ruslan.web.command.factory.CommandFactory;
import by.ruslan.web.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "MainServlet", value = "/main-servlet")
public class MainServlet extends HttpServlet {

    static final Logger logger = LogManager.getLogger();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /*List<User> users = List.of(new User("Johny"), new User("Kate"), new User("Jane"));
        logger.info("User list is created");
        request.setAttribute("list", users);
        request.getRequestDispatcher("/pages/list.jsp").forward(request, response);*/

        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        Optional<Command> optionalValue = CommandFactory.defineCommand(request);
        if (optionalValue.isPresent()){
            Command command = optionalValue.get();
            page = command.execute(request);
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}