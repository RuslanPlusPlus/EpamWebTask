package by.ruslan.web.model.dao;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.entity.SportKind;

import java.util.Optional;

public interface SportKindDao extends BaseDao<SportKind>{
    boolean add(SportKind sportKind) throws DAOException;
    boolean updateSportKind(SportKind sportKind) throws DAOException;
    Optional<SportKind> findSportKindById(long id) throws DAOException;
}
