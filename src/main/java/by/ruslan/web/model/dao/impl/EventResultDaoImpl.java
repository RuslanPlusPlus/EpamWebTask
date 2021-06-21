package by.ruslan.web.model.dao.impl;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.dao.EventMemberColumn;
import by.ruslan.web.model.dao.EventResultColumn;
import by.ruslan.web.model.dao.EventResultDao;
import by.ruslan.web.model.entity.EventMember;
import by.ruslan.web.model.entity.EventResult;
import by.ruslan.web.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventResultDaoImpl implements EventResultDao {

    private static final String SQL_ADD_EVENT_RESULT =
            "INSERT INTO event_results (event_id, winner_id, loser_id, winner_score, loser_score) " +
                    "VALUES (?, ?, ?, ?, ?) ";
    private static final String SQL_FIND_EVENT_RESULT_BY_EVENT =
            "SELECT * FROM event_results WHERE event_id = ? ";
    private static final String SQL_FIND_ALL_EVENT_RESULTS =
            "SELECT * FROM event_results ";

    @Override
    public List<EventResult> findAll() throws DAOException {
        List<EventResult> results = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_EVENT_RESULTS)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                EventResult eventResult = buildEventResult(resultSet);
                results.add(eventResult);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return results;
    }

    @Override
    public boolean add(EventResult eventResult) throws DAOException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_EVENT_RESULT)){
            long eventId = eventResult.getEventId();
            long winnerId = eventResult.getWinnerId();
            long loserId = eventResult.getLoserId();
            int winnerScore = eventResult.getWinnerScore();
            int loserScore = eventResult.getLoserScore();
            statement.setLong(1, eventId);
            statement.setLong(2, winnerId);
            statement.setLong(3, loserId);
            statement.setInt(4, winnerScore);
            statement.setInt(5, loserScore);
            result = statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public Optional<EventResult> findEventResultByEvent(long eventId) throws DAOException{
        Optional<EventResult> resultOptional = Optional.empty();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_EVENT_RESULT_BY_EVENT)) {
            statement.setLong(1, eventId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                EventResult eventResult = buildEventResult(resultSet);
                resultOptional = Optional.of(eventResult);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return resultOptional;
    }

    private EventResult buildEventResult(ResultSet resultSet) throws SQLException {
        EventResult eventResult = new EventResult();
        eventResult.setEventId(resultSet.getLong(EventResultColumn.EVENT_ID));
        eventResult.setWinnerId(resultSet.getLong(EventResultColumn.WINNER_ID));
        eventResult.setLoserId(resultSet.getLong(EventResultColumn.LOSER_ID));
        eventResult.setWinnerScore(resultSet.getInt(EventResultColumn.WINNER_SCORE));
        eventResult.setLoserScore(resultSet.getInt(EventResultColumn.LOSER_SCORE));
        return eventResult;
    }
}
