package by.ruslan.web.model.service;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.exception.EventException;
import by.ruslan.web.exception.MemberException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.Event;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * The {@code EventService} interface represents event service
 *
 * @author Ruslan Nedvedskiy
 */
public interface EventService {

    /**
     * Find all active events
     *
     * @return List of events
     * @throws ServiceException the service exception
     */
    List<Event> findActiveEvents() throws ServiceException;

    /**
     * Find all active events sorted by date
     *
     * @return List of events
     * @throws ServiceException the service exception
     */
    List<Event> findActiveEventsSortedByDate() throws ServiceException;

    /**
     * Find all active events of passed sport kind
     *
     * @param kindId sport kind id
     * @return List of events
     * @throws ServiceException the service exception
     */
    List<Event> findActiveEventsBySportKind(long kindId) throws ServiceException;

    /**
     * Find event by id
     *
     * @param eventId event id
     * @return Optional of Event entity
     * @throws ServiceException the service exception
     */
    Optional<Event> findEventById(long eventId) throws ServiceException;

    /**
     * Add event
     *
     * @param eventName member name
     * @param sportKindId sport kind id
     * @param member1Id member1 id
     * @param member2Id member2 id
     * @param timestamp time and date
     * @return Event entity
     * @throws ServiceException the service exception
     * @throws EventException the member exception
     */
    Event add(String eventName, long sportKindId, long member1Id, long member2Id, Timestamp timestamp) throws ServiceException, EventException;
}
