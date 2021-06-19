package by.ruslan.web.model.service.impl;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.dao.SportKindDao;
import by.ruslan.web.model.dao.impl.SportKindDaoImpl;
import by.ruslan.web.model.entity.SportKind;
import by.ruslan.web.model.service.SportKindService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class SportKindServiceImpl implements SportKindService {

    static final Logger logger = LogManager.getLogger();
    private final SportKindDao sportKindDao = new SportKindDaoImpl();

    @Override
    public boolean add(SportKind sportKind) throws ServiceException {
        boolean result;
        try {
            result = sportKindDao.add(sportKind);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<SportKind> findAll() throws ServiceException {
        List<SportKind> kinds;
        try {
            kinds = sportKindDao.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return kinds;
    }

    @Override
    public Optional<SportKind> findBySportKindById(long kindId) throws ServiceException {
        Optional<SportKind> kindOptional;
        try {
            kindOptional = sportKindDao.findSportKindById(kindId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return kindOptional;
    }

    @Override
    public boolean updateSportKind(SportKind sportKind) throws ServiceException {
        boolean result;
        try {
            result = sportKindDao.updateSportKind(sportKind);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }
}
