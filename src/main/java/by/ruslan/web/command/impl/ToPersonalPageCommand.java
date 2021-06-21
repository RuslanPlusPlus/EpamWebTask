package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.Bet;
import by.ruslan.web.model.entity.Event;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.model.service.EventService;
import by.ruslan.web.model.service.impl.EventServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToPersonalPageCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private EventService eventService;

    public ToPersonalPageCommand(EventService eventService){
        this.eventService = eventService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();

        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        List<Bet> activeBets = user.getActiveBets();
        List<Bet> completedBets = user.getCompletedBets();
        try {
            for (Bet activeBet: activeBets){
                Optional<Event> eventOptional = eventService.findEventById(activeBet.getEventId());
                activeBet.setEventName(eventOptional.get().getEventName());
            }
            for (Bet completedBet: completedBets){
                Optional<Event> eventOptional = eventService.findEventById(completedBet.getEventId());
                completedBet.setEventName(eventOptional.get().getEventName());
            }
        }catch (ServiceException e) {
            logger.error(e.getMessage());
            router.setPath(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
        }

        router.setPath(PagePath.PERSONAL_PAGE);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.TO_PERSONAL_PAGE);
        return router;
    }
}
