package by.ruslan.web.model.service;

import by.ruslan.web.exception.MemberException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.EventMember;


import java.util.List;

public interface EventMemberService {
    List<EventMember> findAll() throws ServiceException;
    boolean add(String memberName, long sportKindId) throws ServiceException, MemberException;
}
