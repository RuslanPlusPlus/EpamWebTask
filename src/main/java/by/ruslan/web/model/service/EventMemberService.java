package by.ruslan.web.model.service;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.exception.MemberException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.EventMember;


import java.util.List;

/**
 * The {@code EventMemberService} interface represents event member service
 *
 * @author Ruslan Nedvedskiy
 */
public interface EventMemberService {

    /**
     * Find all event members
     *
     * @return List of event members
     * @throws ServiceException the dao exception
     */
    List<EventMember> findAll() throws ServiceException;

    /**
     * Add event member
     *
     * @param memberName member name
     * @param sportKindId sport kind id
     * @return boolean Operation success
     * @throws ServiceException the service exception
     * @throws MemberException the member exception
     */
    boolean add(String memberName, long sportKindId) throws ServiceException, MemberException;
}
