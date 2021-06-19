package by.ruslan.web.util;

import by.ruslan.web.command.RequestAttribute;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.SportKind;
import by.ruslan.web.model.service.SportKindService;
import by.ruslan.web.model.service.impl.SportKindServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RequestEditor {

    public static void addSportKindsToRequest(HttpServletRequest request, SportKindService sportKindService) throws ServiceException {
        List<SportKind> sportKinds = sportKindService.findAll();
        //logger.debug(sportKinds);
        request.setAttribute(RequestAttribute.SPORT_KINDS, sportKinds);
    }
}
