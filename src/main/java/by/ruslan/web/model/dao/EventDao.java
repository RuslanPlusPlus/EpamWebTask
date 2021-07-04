package by.ruslan.web.model.dao;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.entity.Event;

import java.util.List;
import java.util.Optional;

/**
 * The {@code EventDao} interface represents event dao
 *
 * @author Ruslan Nedvedskiy
 */

public interface EventDao extends BaseDao<Event>{

    /**
     * Add Event entity to database table.
     *
     * @param event Event entity
     * @return boolean Operation success
     * @throws DAOException the dao exception
     */
    Event add(Event event) throws DAOException;

    /**
     * Find all active events .
     *
     * @return List of events
     * @throws DAOException the dao exception
     */
    List<Event> findAllActiveEvents() throws DAOException;

    /**
     * Find all completed events .
     *
     * @return List of events
     * @throws DAOException the dao exception
     */
    List<Event> findAllCompletedEvents() throws DAOException;

    /**
     * Find all active events of passed sport kind.
     *
     * @return List of events
     * @throws DAOException the dao exception
     */
    List<Event> findAllActiveEventsBySportKind(long kindId) throws DAOException;

    /**
     * Find all active events sorted by date.
     *
     * @return List of events
     * @throws DAOException the dao exception
     */
    List<Event> findAllActiveEventsSortedByDate() throws DAOException;

    /**
     * Find event by passed id of passed sport kind.
     *
     * @param eventId event id
     * @return Optional of Event entity
     * @throws DAOException the dao exception
     */
    Optional<Event> findEventById(long eventId) throws DAOException;

    /**
     * Update Event entity in database table.
     *
     * @param event Event entity
     * @return boolean Operation success
     * @throws DAOException the dao exception
     */
    boolean updateEvent(Event event) throws DAOException;
}
