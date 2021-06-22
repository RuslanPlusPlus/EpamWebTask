package by.ruslan.web.model.service.impl;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.exception.MemberException;
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
    private final String ERROR_MESSAGE_MEMBER_EXISTS = "This sport kind contains member with such name";

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

    @Override
    public boolean add(String memberName, long sportKindId) throws ServiceException, MemberException {
        boolean result;
        MemberException memberException = new MemberException();
        try {
            List<EventMember> membersOfThisKind = eventMemberDao.findEventMembersBySportKind(sportKindId);
            String memberNameToLowCase = memberName.toLowerCase();
            for (EventMember member: membersOfThisKind){
                String name1 = member.getMemberName().toLowerCase();
                if (name1.equals(memberNameToLowCase)){
                    memberException.setErrorMessage(ERROR_MESSAGE_MEMBER_EXISTS);
                    throw memberException;
                }
            }
            EventMember eventMember = new EventMember();
            eventMember.setMemberName(memberName);
            eventMember.setKindId(sportKindId);
            result = eventMemberDao.add(eventMember);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }
}
