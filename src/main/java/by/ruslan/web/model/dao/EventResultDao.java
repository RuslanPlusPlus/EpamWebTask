package by.ruslan.web.model.dao;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.entity.EventResult;

import java.util.Optional;

/**
 * The {@code EventResultDao} interface represents event result dao
 *
 * @author Ruslan Nedvedskiy
 */

public interface EventResultDao extends BaseDao<EventResult>{

    /**
     * Add EventResult entity to database table.
     *
     * @param eventResult EventResult entity
     * @return boolean Operation success
     * @throws DAOException the dao exception
     */
    boolean add(EventResult eventResult) throws DAOException;

    /**
     * Find event result of passed event.
     *
     * @param eventId event id
     * @return Optional of EventResult entity
     * @throws DAOException the dao exception
     */
    Optional<EventResult> findEventResultByEvent(long eventId) throws DAOException;
}
