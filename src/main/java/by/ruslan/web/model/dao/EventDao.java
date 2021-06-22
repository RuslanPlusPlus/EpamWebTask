package by.ruslan.web.model.dao;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventDao extends BaseDao<Event>{
    Event add(Event event) throws DAOException;
    List<Event> findAllActiveEvents() throws DAOException;
    List<Event> findAllCompletedEvents() throws DAOException;
    List<Event> findAllActiveEventsBySportKind(long kindId) throws DAOException;
    List<Event> findAllActiveEventsSortedByDate() throws DAOException;
    Optional<Event> findEventById(long eventId) throws DAOException;
    boolean updateEvent(Event event) throws DAOException;
}
