package by.ruslan.web.model.dao;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.entity.EventResult;

import java.util.Optional;

public interface EventResultDao extends BaseDao<EventResult>{
    boolean add(EventResult eventResult) throws DAOException;
    Optional<EventResult> findEventResultByEvent(long eventId) throws DAOException;
}
