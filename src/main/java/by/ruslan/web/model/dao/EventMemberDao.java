package by.ruslan.web.model.dao;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.entity.EventMember;

import java.util.List;
import java.util.Optional;

public interface EventMemberDao extends BaseDao<EventMember>{
    Optional<EventMember> findEventMemberById(long eventMemberId) throws DAOException;
    List<EventMember> findEventMembersByEvent(long eventId) throws DAOException;
    List<EventMember> findEventMembersBySportKind(long kindId) throws DAOException;
    boolean add(EventMember eventMember) throws DAOException;
    boolean updateEventMember(EventMember eventMember) throws DAOException;
    boolean linkEventMembersToEvent(List<EventMember> members, long eventId) throws DAOException;
}
