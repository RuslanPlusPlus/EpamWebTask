package by.ruslan.web.model.service;

import by.ruslan.web.exception.EventResultException;
import by.ruslan.web.exception.MemberException;
import by.ruslan.web.model.entity.EventResult;
import com.google.protobuf.ServiceException;

/**
 * The {@code EventResultService} interface represents event result service
 *
 * @author Ruslan Nedvedskiy
 */
public interface EventResultService {

    /**
     * Add event member
     *
     * @param eventResult EventResult entity
     * @throws ServiceException the service exception
     * @throws EventResultException the event result exception
     */
    void add(EventResult eventResult) throws ServiceException, EventResultException;
}
