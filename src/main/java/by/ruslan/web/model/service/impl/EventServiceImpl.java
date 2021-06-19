package by.ruslan.web.model.service.impl;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.dao.EventDao;
import by.ruslan.web.model.dao.EventMemberDao;
import by.ruslan.web.model.dao.impl.EventDaoImpl;
import by.ruslan.web.model.dao.impl.EventMemberDaoImpl;
import by.ruslan.web.model.entity.Event;
import by.ruslan.web.model.service.EventService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class EventServiceImpl implements EventService {

    static final Logger logger = LogManager.getLogger();
    private final EventDao eventDao = new EventDaoImpl();
    private final EventMemberDao memberDao = new EventMemberDaoImpl();

    // TODO: 19.06.2021 pagination

    @Override
    public List<Event> findActiveEvents() throws ServiceException {
        List<Event> events;
        try {
            events = eventDao.findAllActiveEvents();
            logger.debug(events);
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
            logger.debug(events);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return events;
    }

    @Override
    public List<Event> findActiveEventsSortedBySportKind(long kindId) throws ServiceException {
        List<Event> events;
        try {
            events = eventDao.findAllActiveEventsBySportKind(kindId);
            logger.debug(events);
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
            logger.debug(eventOptional);
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
