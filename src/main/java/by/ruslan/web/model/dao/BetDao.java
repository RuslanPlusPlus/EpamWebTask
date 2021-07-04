package by.ruslan.web.model.dao;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.entity.Bet;

import java.math.BigDecimal;
import java.util.List;

/**
 * The {@code BetDao} interface represents bet dao
 *
 * @author Ruslan Nedvedskiy
 */

public interface BetDao extends BaseDao<Bet>{

    /**
     * Add Bet entity to database table.
     *
     * @param bet Bet entity
     * @return boolean Operation success
     * @throws DAOException the dao exception
     */
    boolean add(Bet bet) throws DAOException;

    /**
     * Find bets by passed event.
     *
     * @param eventId event id
     * @return List of bets
     * @throws DAOException the dao exception
     */
    List<Bet> findBetsByEvent(long eventId) throws DAOException;

    /**
     * Find active bets of user.
     *
     * @param userId event id
     * @return List of bets
     * @throws DAOException the dao exception
     */
    List<Bet> findActiveBetsForUser(long userId) throws DAOException;

    /**
     * Find completed bets of user.
     *
     * @param userId event id
     * @return List of bets
     * @throws DAOException the dao exception
     */
    List<Bet> findCompletedBetsForUser(long userId) throws DAOException;

    /**
     * set win money for bet.
     *
     * @param bet Bet entity
     * @return boolean Operation success
     * @throws DAOException the dao exception
     */
    boolean setWinMoney(Bet bet) throws DAOException;
}
