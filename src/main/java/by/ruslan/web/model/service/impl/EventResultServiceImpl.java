package by.ruslan.web.model.service.impl;


import by.ruslan.web.exception.DAOException;
import by.ruslan.web.exception.EventResultException;
import by.ruslan.web.model.dao.BetDao;
import by.ruslan.web.model.dao.EventDao;
import by.ruslan.web.model.dao.EventResultDao;
import by.ruslan.web.model.dao.UserDao;
import by.ruslan.web.model.dao.impl.BetDaoImpl;
import by.ruslan.web.model.dao.impl.EventDaoImpl;
import by.ruslan.web.model.dao.impl.EventResultDaoImpl;
import by.ruslan.web.model.dao.impl.UserDaoImpl;
import by.ruslan.web.model.entity.Bet;
import by.ruslan.web.model.entity.Event;
import by.ruslan.web.model.entity.EventResult;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.model.service.EventResultService;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EventResultServiceImpl implements EventResultService {
    static final Logger logger = LogManager.getLogger();
    private static final double DRAW_COEF = 1.5;
    private static final double WIN_COEF = 2.0;
    private static final double EXACT_SCORE_COEF = 3.5;
    private static final String WINNER_LOSER_EQUAL = "Winner and loser must be different members";

    private final EventResultDao eventResultDao = new EventResultDaoImpl();
    private final BetDao betDao = new BetDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    private final EventDao eventDao = new EventDaoImpl();


    @Override
    public void add(EventResult eventResult) throws ServiceException, EventResultException {
        boolean result;
        EventResultException eventResultException = new EventResultException();
        if (eventResult.getWinnerId() == eventResult.getLoserId()){
            eventResultException.setErrorMessage(WINNER_LOSER_EQUAL);
            throw eventResultException;
        }

        try {
            distributeWinMoney(eventResult);
            eventResultDao.add(eventResult);
            Optional<Event> eventOptional = eventDao.findEventById(eventResult.getEventId());
            if (eventOptional.isPresent()){
                Event event = eventOptional.get();
                event.setStatus(Event.EventStatus.COMPLETED);
                eventDao.updateEvent(event);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private void distributeWinMoney(EventResult eventResult) throws DAOException {
        List<Bet> bets = betDao.findBetsByEvent(eventResult.getEventId());
        List<Bet> winBets;
        winBets = bets.stream().filter(x-> determineWin(x, eventResult)).collect(Collectors.toList());
        for (Bet bet: bets){
            bet.setWinMoney(BigDecimal.valueOf(0.0));
        }
        for (Bet bet: winBets){
            switch (bet.getType()) {
                case DRAW -> {
                    BigDecimal winMoney = BigDecimal.valueOf(DRAW_COEF * bet.getMoney().doubleValue());
                    bet.setWinMoney(winMoney);
                }
                case WIN -> {
                    BigDecimal winMoney = BigDecimal.valueOf(WIN_COEF * bet.getMoney().doubleValue());
                    bet.setWinMoney(winMoney);
                }
                case EXACT_SCORE -> {
                    BigDecimal winMoney = BigDecimal.valueOf(EXACT_SCORE_COEF * bet.getMoney().doubleValue());
                    bet.setWinMoney(winMoney);
                }
            }
        }
        logger.debug(bets);
        for (Bet bet: bets){
            betDao.setWinMoney(bet);
            Optional<User> optionalUser = userDao.findUserById(bet.getUserId());
            if (optionalUser.isPresent()){
                User user = optionalUser.get();
                double balance = user.getBalance().doubleValue();
                logger.debug(balance);
                balance += bet.getWinMoney().doubleValue();
                user.setBalance(BigDecimal.valueOf(balance));
                userDao.update(user);
            }
        }

    }

    private boolean determineWin(Bet bet, EventResult eventResult){
        Bet.BetType betType = bet.getType();
        switch (betType){
            case WIN -> {
                return eventResult.getWinnerId() == bet.getMember1Id();
            }
            case EXACT_SCORE -> {
                /*logger.debug(eventResult.getWinnerId());
                logger.debug(eventResult.getWinnerScore());
                logger.debug(eventResult.getLoserId());
                logger.debug(eventResult.getLoserScore());
                logger.debug(bet.getMember1Id());
                logger.debug(bet.getMember2Id());
                logger.debug(bet.getMember1Score());
                logger.debug(bet.getMember2Score());*/
                if (eventResult.getWinnerId() == bet.getMember1Id()){
                    if (eventResult.getWinnerScore() == bet.getMember1Score() &&
                            eventResult.getLoserScore() == bet.getMember2Score()){
                        logger.debug("1 varik");
                        return true;
                    }
                }else {
                    if (eventResult.getWinnerScore() == bet.getMember2Score() &&
                            eventResult.getLoserScore() == bet.getMember1Score()){
                        logger.debug("2 varik");
                        return true;
                    }
                }
                return false;
            }
            case DRAW -> {
                return eventResult.getLoserScore() == eventResult.getWinnerScore();
            }
        }
        return false;
    }
}
