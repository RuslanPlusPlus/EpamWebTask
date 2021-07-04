package by.ruslan.web.model.service;

import by.ruslan.web.exception.EventException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.exception.SportKindException;
import by.ruslan.web.model.entity.SportKind;

import java.util.List;
import java.util.Optional;

/**
 * The {@code SportKindService} interface represents sport kind service
 *
 * @author Ruslan Nedvedskiy
 */
public interface SportKindService {

    /**
     * Add event
     *
     * @param kindName sport kind name
     * @return boolean success
     * @throws ServiceException the service exception
     * @throws SportKindException the sport kind exception
     */
    boolean add(String kindName) throws ServiceException, SportKindException;

    /**
     * Find all active sport kinds
     *
     * @return List of sport kinds
     * @throws ServiceException the service exception
     */
    List<SportKind> findAll() throws ServiceException;

    /**
     * Find sport kind by id
     *
     * @param kindId sport kind id
     * @return Optional of SportKind entity
     * @throws ServiceException the service exception
     */
    Optional<SportKind> findBySportKindById(long kindId) throws ServiceException;

    /**
     * Update sport kind
     *
     * @param sportKind sport kind
     * @return boolean success
     * @throws ServiceException the service exception
     */
    boolean updateSportKind(SportKind sportKind) throws ServiceException;
}
