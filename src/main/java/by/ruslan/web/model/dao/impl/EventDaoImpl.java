package by.ruslan.web.model.dao.impl;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.dao.EventColumn;
import by.ruslan.web.model.dao.EventDao;

import by.ruslan.web.model.dao.SportKindColumn;
import by.ruslan.web.model.entity.Event;

import by.ruslan.web.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventDaoImpl implements EventDao {

    static final Logger logger = LogManager.getLogger();
    private static final String SQL_SELECT_ALL_EVENTS =
            "SELECT * FROM events JOIN sport_kinds ON events.sport_kind_id = sport_kinds.kind_id ";
    private static final String SQL_FIND_EVENT_BY_ID =
            "SELECT * FROM events JOIN sport_kinds ON events.sport_kind_id = sport_kinds.kind_id WHERE event_id = ?";
    private static final String SQL_SELECT_ALL_ACTIVE_EVENTS =
            "SELECT * FROM events JOIN sport_kinds ON events.sport_kind_id = sport_kinds.kind_id " +
                    "WHERE event_status = 'ACTIVE' OR date > now()";
    private static final String SQL_SELECT_ALL_COMPLETED_EVENTS =
            "SELECT * FROM events JOIN sport_kinds ON events.sport_kind_id = sport_kinds.kind_id " +
                    "WHERE event_status = 'COMPLETED' AND date < now()";
    private static final String SQL_SELECT_ALL_ACTIVE_EVENTS_SORTED_BY_DATE =
            "SELECT * FROM events JOIN sport_kinds ON events.sport_kind_id = sport_kinds.kind_id " +
                    "WHERE event_status = 'ACTIVE' OR date > now() ORDER BY date";
    private static final String SQL_SELECT_ALL_ACTIVE_EVENTS_BY_SPORT_KIND_ID =
            "SELECT * FROM events JOIN sport_kinds ON events.sport_kind_id = sport_kinds.kind_id " +
                    "WHERE (event_status = 'ACTIVE' OR date > now()) AND sport_kind_id = ?";
    private static final String SQL_ADD_EVENT =
            "INSERT INTO events (sport_kind_id, event_name, date, event_status) " +
                    "VALUES (?, ?, ?, ?);";
    private static final String SQL_UPDATE_EVENT =
            "UPDATE events SET sport_kind_id = ?, event_name = ?, date = ?, event_status = ? "+
                    "WHERE event_id = ?";
    @Override
    public List<Event> findAll() throws DAOException {
        return executeSqlRequest(SQL_SELECT_ALL_EVENTS);
    }

    @Override
    public boolean add(Event event) throws DAOException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_EVENT)){
            long sport_kind_id = event.getSportKindId();
            String eventName = event.getEventName();
            Timestamp date = new Timestamp(event.getDate().getTime());
            //status is set in service
            String status = event.getStatus().getValue();
            statement.setLong(1, sport_kind_id);
            statement.setString(2, eventName);
            statement.setTimestamp(3, date);
            statement.setString(4, status);
            result = statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public List<Event> findAllActiveEvents() throws DAOException {
        return executeSqlRequest(SQL_SELECT_ALL_ACTIVE_EVENTS);
    }

    @Override
    public List<Event> findAllCompletedEvents() throws DAOException {
        return executeSqlRequest(SQL_SELECT_ALL_COMPLETED_EVENTS);
    }

    @Override
    public List<Event> findAllActiveEventsBySportKind(long kind_id) throws DAOException {
        List<Event> events = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_ACTIVE_EVENTS_BY_SPORT_KIND_ID)) {
            statement.setLong(1, kind_id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Event event = buildEvent(resultSet);
                events.add(event);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return events;
    }

    @Override
    public List<Event> findAllActiveEventsSortedByDate() throws DAOException {
        return executeSqlRequest(SQL_SELECT_ALL_ACTIVE_EVENTS_SORTED_BY_DATE);
    }

    @Override
    public Optional<Event> findEventById(long eventId) throws DAOException {
        Optional<Event> eventOptional = Optional.empty();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_EVENT_BY_ID)) {
            statement.setLong(1, eventId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Event event = buildEvent(resultSet);
                eventOptional = Optional.of(event);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return eventOptional;
    }

    @Override
    public boolean updateEvent(Event event) throws DAOException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_EVENT)){
            String eventName = event.getEventName();
            long sportKindId = event.getSportKindId();
            Timestamp timestamp = new Timestamp(event.getTime().getTime() + event.getDate().getTime());
            String status = event.getStatus().getValue();
            statement.setLong(1, sportKindId);
            statement.setString(2, eventName);
            statement.setTimestamp(3, timestamp);
            statement.setString(4, status);
            statement.setLong(5, event.getEventId());
            result = statement.executeUpdate() > 0;
            logger.debug("EVVVVVENT UPDDDDAAAATE");
        } catch (SQLException e) {
            throw new DAOException();
        }
        return result;
    }

    private List<Event> executeSqlRequest(String request) throws DAOException{
        List<Event> events = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(request)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Event event = buildEvent(resultSet);
                events.add(event);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return events;
    }


    private Event buildEvent(ResultSet resultSet) throws SQLException {
        long eventId = resultSet.getLong(EventColumn.EVENT_ID);
        long sportKindId = resultSet.getLong(EventColumn.SPORT_KIND_ID);
        String eventName = resultSet.getString(EventColumn.EVENT_NAME);
        String sportKindName = resultSet.getString(SportKindColumn.KIND_NAME);
        //TimeStamp object contains date and time
        Date date = resultSet.getDate(EventColumn.DATE);
        Time time = resultSet.getTime(EventColumn.DATE);
        Event.EventStatus status = Event.EventStatus.valueOf(resultSet.getString(EventColumn.EVENT_STATUS));
        Event event = new Event();
        event.setEventId(eventId);
        event.setEventName(eventName);
        event.setSportKindId(sportKindId);
        event.setSportKindName(sportKindName);
        event.setDate(date);
        event.setTime(time);
        event.setStatus(status);
        //members are set in service
        return event;
    }
}
