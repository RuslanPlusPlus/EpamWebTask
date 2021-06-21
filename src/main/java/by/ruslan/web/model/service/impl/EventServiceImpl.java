package by.ruslan.web.model.service.impl;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.dao.EventDao;
import by.ruslan.web.model.dao.EventMemberDao;
import by.ruslan.web.model.dao.EventResultDao;
import by.ruslan.web.model.dao.impl.EventDaoImpl;
import by.ruslan.web.model.dao.impl.EventMemberDaoImpl;
import by.ruslan.web.model.dao.impl.EventResultDaoImpl;
import by.ruslan.web.model.entity.Event;
import by.ruslan.web.model.entity.EventMember;
import by.ruslan.web.model.entity.EventResult;
import by.ruslan.web.model.service.EventService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EventServiceImpl implements EventService {

    static final Logger logger = LogManager.getLogger();
    private final EventDao eventDao = new EventDaoImpl();
    private final EventMemberDao memberDao = new EventMemberDaoImpl();
    private final EventResultDao eventResultDao = new EventResultDaoImpl();

    // TODO: 19.06.2021 pagination

    @Override
    public List<Event> findActiveEvents() throws ServiceException {
        List<Event> events;
        try {
            events = eventDao.findAllActiveEvents();
            //logger.debug(events);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return events;
    }

    @Override
    public List<Event> findActiveEventsSortedByDate() throws ServiceException {
        List<Event> events;
        try {
            events = eventDao.findAllActiveEventsSortedByDate();
            //logger.debug(events);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return events;
    }

    @Override
    public List<Event> findActiveEventsBySportKind(long kindId) throws ServiceException {
        List<Event> events;
        try {
            events = eventDao.findAllActiveEventsBySportKind(kindId);
            //logger.debug(events);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return events;
    }

    @Override
    public Optional<Event> findEventById(long eventId) throws ServiceException {
        Optional<Event> eventOptional;
        try {
            eventOptional = eventDao.findEventById(eventId);
            if (eventOptional.isPresent()){
                List<EventMember> members = memberDao.findEventMembersByEvent(eventId);
                Event event = eventOptional.get();
                Optional<EventResult> eventResultOptional = eventResultDao.findEventResultByEvent(eventId);

                if (eventResultOptional.isPresent()){
                    EventResult eventResult = eventResultOptional.get();
                    Optional<EventMember> winner = memberDao.findEventMemberById(eventResult.getWinnerId());
                    Optional<EventMember> loser = memberDao.findEventMemberById(eventResult.getLoserId());
                    String winnerName = winner.get().getMemberName();
                    String loserName = loser.get().getMemberName();
                    event.setEventResult(eventResult);
                    eventResult.setWinnerName(winnerName);
                    eventResult.setLoserName(loserName);
                }
                event.setMembers(members);
                long eventTime = event.getDate().getTime();
                long currentTime = new Date().getTime();
                if (eventTime > currentTime && event.getStatus() == Event.EventStatus.ACTIVE){
                    event.setReadyToBet(true);
                    logger.debug("Event " + eventId + " is ready to bet");
                }else if(eventTime < currentTime && event.getStatus() == Event.EventStatus.ACTIVE){
                    event.setReadyToAddResult(true);
                    logger.debug("Event " + eventId + " is ready to be added the result");
                }
            }
            //logger.debug(eventOptional);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return eventOptional;
    }

    @Override
    public boolean add(Event event) throws DAOException {
        boolean result;
        event.setStatus(Event.EventStatus.ACTIVE);
        result = eventDao.add(event);
        return result;
    }
}
