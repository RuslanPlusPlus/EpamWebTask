package by.ruslan.web.model.service.impl;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.dao.EventDao;
import by.ruslan.web.model.dao.EventMemberDao;
import by.ruslan.web.model.dao.impl.EventDaoImpl;
import by.ruslan.web.model.dao.impl.EventMemberDaoImpl;
import by.ruslan.web.model.entity.EventMember;
import by.ruslan.web.model.service.EventMemberService;;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EventMemberServiceImpl implements EventMemberService {

    static final Logger logger = LogManager.getLogger();
    private final EventMemberDao eventMemberDao = new EventMemberDaoImpl();

    @Override
    public List<EventMember> findAll() throws ServiceException {
        List<EventMember> members;
        try {
            members = eventMemberDao.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return members;
    }
}
