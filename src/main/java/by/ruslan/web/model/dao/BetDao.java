package by.ruslan.web.model.dao;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.entity.Bet;

import java.math.BigDecimal;
import java.util.List;

public interface BetDao extends BaseDao<Bet>{
    boolean add(Bet bet) throws DAOException;
    List<Bet> findBetsByEvent(long eventId) throws DAOException;
    List<Bet> findActiveBetsForUser(long userId) throws DAOException;
    List<Bet> findCompletedBetsForUser(long userId) throws DAOException;
    boolean setWinMoney(Bet bet) throws DAOException;
    //BigDecimal findBetMoneyForEvent(long eventId) throws DAOException;

}
