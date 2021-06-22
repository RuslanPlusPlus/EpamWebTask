package by.ruslan.web.model.service.impl;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.exception.SportKindException;
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
    private final String ERROR_MESSAGE_SPORT_KIND_EXISTS = "Such sportKind already exists!";


    @Override
    public boolean add(String kindName) throws ServiceException, SportKindException {
        boolean result;
        SportKindException sportKindException = new SportKindException();
        try {
            /// TODO: 22.06.2021 field validation
            kindName = kindName.toLowerCase();
            List<SportKind> sportKinds = sportKindDao.findAll();
            for (SportKind sportKind: sportKinds){
                if (sportKind.getKindName().equals(kindName)){
                    sportKindException.setErrorMessage(ERROR_MESSAGE_SPORT_KIND_EXISTS);
                    throw sportKindException;
                }
            }
            SportKind sportKind = new SportKind();
            sportKind.setKindName(kindName);
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
