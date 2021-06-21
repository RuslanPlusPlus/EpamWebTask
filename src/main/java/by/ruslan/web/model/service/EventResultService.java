package by.ruslan.web.model.service;

import by.ruslan.web.exception.EventResultException;
import by.ruslan.web.model.entity.EventResult;
import com.google.protobuf.ServiceException;

public interface EventResultService {
    void add(EventResult eventResult) throws ServiceException, EventResultException;
}
