package by.ruslan.web.model.dao;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.entity.SportKind;

import java.util.Optional;

/**
 * The {@code SportKindDao} interface represents sport kind dao
 *
 * @author Ruslan Nedvedskiy
 */

public interface SportKindDao extends BaseDao<SportKind>{

    /**
     * Add SportKind entity to database table.
     *
     * @param sportKind SportKind entity
     * @return boolean Operation success
     * @throws DAOException the dao exception
     */
    boolean add(SportKind sportKind) throws DAOException;

    /**
     * Update SportKind entity in database table.
     *
     * @param sportKind SportKind entity
     * @return boolean Operation success
     * @throws DAOException the dao exception
     */
    boolean updateSportKind(SportKind sportKind) throws DAOException;

    /**
     * Find sport kind by passed id.
     *
     * @param id sport kind id
     * @return Optional of SportKind entity
     * @throws DAOException the dao exception
     */
    Optional<SportKind> findSportKindById(long id) throws DAOException;
}
