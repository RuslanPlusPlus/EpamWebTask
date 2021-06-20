<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 20.06.2021
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container_s">
<c:import url="fragments/header.jsp"/>
<div class="main">
    <!-- left menu -->
    <div class="center-part">

        <div class="event-block">
            <h5 class="event-name"><c:out value="${requestScope.event.eventName}" /></h5>
            <p class="event-sport_kind">${requestScope.event.sportKindName}</p>
            <time><fmt:message key="event.startDate"/> ${requestScope.event.date} ${requestScope.event.time}</time>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-2 m-2">
                    <div class="list-group">
                        <a href="${pageContext.request.contextPath}/main-servlet?command=to_draw_bet_page&eventId=${requestScope.event.eventId}"
                           class="make-bet-link">
                            <fmt:message key="bet.draw"/>
                        </a>
                        <br/>
                        <a href="${pageContext.request.contextPath}/main-servlet?command=to_win_bet_page&eventId=${requestScope.event.eventId}"
                           class="make-bet-link">
                            <fmt:message key="bet.win"/>
                        </a>
                        <br/>
                        <a href="${pageContext.request.contextPath}/main-servlet?command=to_exact_score_bet_page&eventId=${requestScope.event.eventId}"
                           class="make-bet-link">
                            <fmt:message key="bet.exactScore"/>
                        </a>
                        <br/>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
</div>
</body>
</html>
