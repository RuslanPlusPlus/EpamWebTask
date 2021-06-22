package by.ruslan.web.model.service;

import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.exception.SportKindException;
import by.ruslan.web.model.entity.SportKind;

import java.util.List;
import java.util.Optional;

public interface SportKindService {
    boolean add(String kindName) throws ServiceException, SportKindException;
    List<SportKind> findAll() throws ServiceException;
    Optional<SportKind> findBySportKindById(long kindId) throws ServiceException;
    boolean updateSportKind(SportKind sportKind) throws ServiceException;
}
