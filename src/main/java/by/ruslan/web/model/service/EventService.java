package by.ruslan.web.model.service;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> findActiveEvents() throws ServiceException;
    List<Event> findActiveEventsSortedByDate() throws ServiceException;
    List<Event> findActiveEventsBySportKind(long kindId) throws ServiceException;
    Optional<Event> findEventById(long eventId) throws ServiceException;
    boolean add(Event event) throws DAOException;
}
