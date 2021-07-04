package by.ruslan.web.model.dao;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.entity.EventMember;

import java.util.List;
import java.util.Optional;

/**
 * The {@code EventMemberDao} interface represents event member dao
 *
 * @author Ruslan Nedvedskiy
 */

public interface EventMemberDao extends BaseDao<EventMember>{

    /**
     * Find event member by passed id.
     *
     * @param eventMemberId event member id
     * @return Optional of EventMember entity
     * @throws DAOException the dao exception
     */
    Optional<EventMember> findEventMemberById(long eventMemberId) throws DAOException;

    /**
     * Find all event members by passed event events .
     *
     * @param eventId event id
     * @return List of event members
     * @throws DAOException the dao exception
     */
    List<EventMember> findEventMembersByEvent(long eventId) throws DAOException;

    /**
     * Find all event members by passed event events .
     *
     * @param kindId sport kind id
     * @return List of event members
     * @throws DAOException the dao exception
     */
    List<EventMember> findEventMembersBySportKind(long kindId) throws DAOException;

    /**
     * Add EventMember entity to database table.
     *
     * @param eventMember EventMember entity
     * @return boolean Operation success
     * @throws DAOException the dao exception
     */
    boolean add(EventMember eventMember) throws DAOException;

    /**
     * Update EventMember entity in database table.
     *
     * @param eventMember EventMember entity
     * @return boolean Operation success
     * @throws DAOException the dao exception
     */
    boolean updateEventMember(EventMember eventMember) throws DAOException;

    /**
     * Link event members to corresponding event as participants.
     *
     * @param members List of event members
     * @param eventId event id
     * @throws DAOException the dao exception
     */
    void linkEventMembersToEvent(List<EventMember> members, long eventId) throws DAOException;
}
