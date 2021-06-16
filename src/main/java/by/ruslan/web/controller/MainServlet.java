package by.ruslan.web.controller;

import by.ruslan.web.command.*;
import by.ruslan.web.model.pool.ConnectionPool;
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
        logger.debug("get method");
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("post method");
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Command> optionalCommand = CommandFactory.defineCommand(request);
        if (optionalCommand.isPresent()){
            Command command = optionalCommand.get();
            Router router = command.execute(request);
            String page = router.getPath();
            logger.debug(page);
            HttpSession session = request.getSession();
            session.setAttribute(SessionAttribute.CURRENT_PAGE, page);
            //session.setAttribute(SessionAttribute.LOCALE, SessionAttribute.ENGLISH);
            switch (router.getType()){
                case FORWARD -> {
                    request.getRequestDispatcher(page).forward(request, response);
                }
                case REDIRECT -> {
                    response.sendRedirect(request.getContextPath() + page);
                }
            }
        }else {
            request.setAttribute(RequestAttribute.ERROR, "Failed to determine command");
            response.sendError(500);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.getInstance().destroyPool();
    }
}