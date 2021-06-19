package by.ruslan.web.model.dao.impl;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.dao.EventMemberColumn;
import by.ruslan.web.model.dao.EventMemberDao;
import by.ruslan.web.model.entity.EventMember;
import by.ruslan.web.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventMemberDaoImpl implements EventMemberDao {

    static final Logger logger = LogManager.getLogger();
    private static final String SQL_SELECT_ALL_EVENT_MEMBERS =
            "SELECT * FROM event_members JOIN sport_kinds ON kind_id = sport_kinds.kind_id";
    private static final String SQL_FIND_EVENT_MEMBER_BY_ID =
            "SELECT * FROM event_members JOIN sport_kinds ON kind_id = sport_kinds.kind_id " +
                    "WHERE member_id = ?";
    private static final String SQL_FIND_EVENT_MEMBERS_BY_EVENT =
            "SELECT * FROM event_members JOIN even_link_eventmember ON member_id = even_link_eventmember.member_id" +
                    "WHERE even_link_eventmember.event_id = ?";
    private static final String SQL_FIND_EVENT_MEMBER_BY_SPORT_KIND_ID =
            "SELECT * FROM event_members JOIN sport_kinds ON kind_id = sport_kinds.kind_id" +
                    "WHERE sport_kind_id = ?";
    private static final String SQL_LINK_MEMBER_TO_EVENT =
            "INSERT INTO even_link_eventmember (event_id, member_id) " +
                    "VALUES (?, ?)";
    private static final String SQL_ADD_EVENT_MEMBER =
            "INSERT INTO event_members (member_name, kind_id) " +
                    "VALUES (?, ?);";
    private static final String SQL_UPDATE_EVENT_MEMBER =
            "UPDATE event_members SET member_name = ?, kind_id = ? " +
                    "WHERE member_id = ?";
    @Override
    public List<EventMember> findAll() throws DAOException {
        List<EventMember> members = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_EVENT_MEMBERS)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                EventMember eventMember = buildEventMember(resultSet);
                members.add(eventMember);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return members;
    }

    @Override
    public Optional<EventMember> findEventMemberById(long eventMemberId) throws DAOException {
        Optional<EventMember> memberOptional = Optional.empty();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_EVENT_MEMBER_BY_ID)) {
            statement.setLong(1, eventMemberId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                EventMember eventMember = buildEventMember(resultSet);
                memberOptional = Optional.of(eventMember);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return memberOptional;
    }

    @Override
    public List<EventMember> findEventMembersByEvent(long eventId) throws DAOException {
        return executeSqlRequestWithId(SQL_FIND_EVENT_MEMBERS_BY_EVENT, eventId);
    }

    @Override
    public List<EventMember> findEventMembersBySportKind(long kindId) throws DAOException {
        return executeSqlRequestWithId(SQL_FIND_EVENT_MEMBER_BY_SPORT_KIND_ID, kindId);
    }

    @Override
    public boolean add(EventMember eventMember) throws DAOException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_EVENT_MEMBER)){
            long kindId = eventMember.getKindId();
            String memberName = eventMember.getMemberName();
            statement.setLong(1, kindId);
            statement.setString(2, memberName);
            result = statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public boolean updateEventMember(EventMember eventMember) throws DAOException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_EVENT_MEMBER)){
            long kindId = eventMember.getKindId();
            String memberName = eventMember.getMemberName();
            statement.setLong(1, kindId);
            statement.setString(2, memberName);
            statement.setLong(3, eventMember.getMemberId());
            result = statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public boolean linkEventMembersToEvent(List<EventMember> members, long eventId) throws DAOException {
        boolean result = true;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_LINK_MEMBER_TO_EVENT)){
            for (EventMember member: members){
                long memberId = member.getMemberId();
                statement.setLong(1, eventId);
                statement.setLong(2, memberId);
                if (statement.executeUpdate() < 1){
                    result = false;
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

    private List<EventMember> executeSqlRequestWithId(String request, long id) throws DAOException{
        List<EventMember> members = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                EventMember eventMember = buildEventMember(resultSet);
                members.add(eventMember);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return members;
    }

    private EventMember buildEventMember(ResultSet resultSet) throws SQLException {
        long memberId = resultSet.getLong(EventMemberColumn.MEMBER_ID);
        long kindId = resultSet.getLong(EventMemberColumn.KIND_ID);
        String memberName = resultSet.getString(EventMemberColumn.MEMBER_NAME);
        EventMember eventMember = new EventMember();
        eventMember.setMemberId(memberId);
        eventMember.setMemberName(memberName);
        eventMember.setKindId(kindId);
        return eventMember;
    }
}
