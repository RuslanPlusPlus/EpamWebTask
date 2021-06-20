package by.ruslan.web.model.dao.impl;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.dao.BetColumn;
import by.ruslan.web.model.dao.BetDao;
import by.ruslan.web.model.dao.EventMemberColumn;
import by.ruslan.web.model.dao.UsersColumn;
import by.ruslan.web.model.entity.Bet;
import by.ruslan.web.model.entity.EventMember;
import by.ruslan.web.model.pool.ConnectionPool;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BetDaoImpl implements BetDao {

    private enum SQL_ADD_REQUEST_TYPE{
        DRAW ("INSERT INTO bets (bet_type, money, user_id, event_id) " +
                "VALUES (?, ?, ?, ?)"),
        WIN("INSERT INTO bets (bet_type, money, user_id, event_id, event_member1_id) " +
                "VALUES (?, ?, ?, ?, ?)"),
        EXACT_SCORE("INSERT INTO bets (bet_type, money, user_id, event_id, " +
                "event_member1_id, event_member2_id, event_member1_score, event_member2_score) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

        SQL_ADD_REQUEST_TYPE(String value){
            this.value = value;
        }

        private String value;

        public String getValue() {
            return value;
        }
    }
    private static final String SQL_SELECT_ALL_BETS =
            "SELECT * FROM bets";
    private static final String SQL_FIND_BETS_BY_EVENT =
            "SELECT * FROM bets WHERE event_id = ?";
    private static final String SQL_SET_WIN_MONEY =
            "UPDATE bets SET win_money = ? WHERE bet_id = ?";
    private static final String SQL_FIND_BET_MONEY_FOR_EVENT =
            "SELECT money FROM bets WHERE event_id = ?";
    private static final String SQL_FIND_ACTIVE_BETS_FOR_USER =
            "SELECT * FROM bets JOIN users ON bets.user_id = users.user_id WHERE bets.user_id = ? AND win_money IS NULL";
    private static final String SQL_FIND_NOT_ACTIVE_BETS_FOR_USER =
            "SELECT * FROM bets WHERE user_id = ? AND win_money IS NOT NULL";

    @Override
    public List<Bet> findAll() throws DAOException {
        List<Bet> bets = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_BETS)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                long betId = resultSet.getLong(BetColumn.BET_ID);
                Bet.BetType betType = Bet.BetType.valueOf(resultSet.getString(BetColumn.BET_TYPE));
                BigDecimal money = resultSet.getBigDecimal(BetColumn.MONEY);
                BigDecimal win_money = resultSet.getBigDecimal(BetColumn.WIN_MONEY);
                long userId = resultSet.getLong(BetColumn.USER_ID);
                long eventId = resultSet.getLong(BetColumn.EVENT_ID);
                Bet bet = new Bet();
                bet.setBetId(betId);
                bet.setType(betType);
                bet.setMoney(money);
                bet.setWinMoney(win_money);
                bet.setUserId(userId);
                bet.setEventId(eventId);
                bets.add(bet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return bets;
    }

    @Override
    public boolean add(Bet bet) throws DAOException {
        boolean result;
        Bet.BetType type = bet.getType();
        String sqlRequest = null;
        switch (type){
            case WIN: {
                sqlRequest = SQL_ADD_REQUEST_TYPE.WIN.value;
                break;
            }
            case DRAW: {
                sqlRequest = SQL_ADD_REQUEST_TYPE.DRAW.value;
                break;
            }
            case EXACT_SCORE:{
                sqlRequest = SQL_ADD_REQUEST_TYPE.EXACT_SCORE.value;
                break;
            }
        }
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlRequest)){
            statement.setString(1, bet.getType().getValue());
            statement.setBigDecimal(2, bet.getMoney());
            statement.setLong(3, bet.getUserId());
            statement.setLong(4, bet.getEventId());
            if (type == Bet.BetType.WIN){
                statement.setLong(5, bet.getMember1Id());
            }
            if (type == Bet.BetType.EXACT_SCORE){
                statement.setLong(5, bet.getMember1Id());
                statement.setLong(6, bet.getMember2Id());
                statement.setInt(7, bet.getMember1Score());
                statement.setInt(8, bet.getMember2Score());
            }
            result = statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public List<Bet> findBetsByEvent(long eventId) throws DAOException {
        List<Bet> bets = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BETS_BY_EVENT)) {
            statement.setLong(1, eventId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Bet bet = new Bet();
                bet.setEventId(resultSet.getLong(BetColumn.EVENT_ID));
                bet.setUserId(resultSet.getLong(BetColumn.USER_ID));
                bet.setMoney(resultSet.getBigDecimal(BetColumn.MONEY));
                bet.setMember1Id(resultSet.getLong(BetColumn.EVENT_MEMBER1_ID));
                bet.setMember2Id(resultSet.getLong(BetColumn.EVENT_MEMBER2_ID));
                bet.setMember2Id(resultSet.getInt(BetColumn.EVENT_MEMBER1_SCORE));
                bet.setMember2Id(resultSet.getInt(BetColumn.EVENT_MEMBER2_SCORE));
                Bet.BetType type = Bet.BetType.valueOf(resultSet.getString(BetColumn.BET_TYPE));
                bet.setType(type);
                bets.add(bet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return bets;
    }

    @Override
    public List<Bet> findActiveBetsForUser(long userId) throws DAOException {
        List<Bet> bets = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ACTIVE_BETS_FOR_USER)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Bet bet = new Bet();
                bet.setEventId(resultSet.getLong(BetColumn.EVENT_ID));
                bet.setUserId(resultSet.getLong(BetColumn.USER_ID));
                bet.setMoney(resultSet.getBigDecimal(BetColumn.MONEY));
                Bet.BetType type = Bet.BetType.valueOf(resultSet.getString(BetColumn.BET_TYPE));
                bet.setType(type);
                bet.setUserEmail(resultSet.getString(UsersColumn.EMAIL));
                bets.add(bet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return bets;
    }

    @Override
    public List<Bet> findNotActiveBetsForUser(long userId) throws DAOException {
        List<Bet> bets = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ACTIVE_BETS_FOR_USER)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Bet bet = new Bet();
                bet.setEventId(resultSet.getLong(BetColumn.EVENT_ID));
                bet.setUserId(resultSet.getLong(BetColumn.USER_ID));
                bet.setMoney(resultSet.getBigDecimal(BetColumn.MONEY));
                bet.setWinMoney(resultSet.getBigDecimal(BetColumn.WIN_MONEY));
                Bet.BetType type = Bet.BetType.valueOf(resultSet.getString(BetColumn.BET_TYPE));
                bets.add(bet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return bets;
    }

    @Override
    public boolean setWinMoney(Bet bet) throws DAOException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SET_WIN_MONEY)){
            statement.setBigDecimal(1, bet.getWinMoney());
            statement.setLong(2, bet.getBetId());
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public BigDecimal findBetMoneyForEvent(long eventId) throws DAOException {
        return null;
    }

}
