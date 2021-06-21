<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 19.06.2021
  Time: 12:29
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
        <div class="center-part">
            <div class="event-block">
                <div class="event-header">
                    <div>
                        <h5 class="event-name"><c:out value="${requestScope.event.eventName}" /></h5>
                        <p class="event-sport_kind">${requestScope.event.sportKindName}</p>
                        <time><fmt:message key="event.startDate"/> ${requestScope.event.date} ${requestScope.event.time}</time>

                        <c:if test="${not empty requestScope.error}">
                            <div class="error-div">
                                    ${requestScope.error}
                            </div>
                        </c:if>

                        <c:if test="${not empty requestScope.success}">
                            <div class="success-div">
                                    ${requestScope.success}
                            </div>
                        </c:if>

                        <c:if test="${requestScope.event.status eq 'COMPLETED'}">
                            <div class="event-result">
                                <p>${requestScope.event.eventResult.winnerName}(${requestScope.event.eventResult.winnerScore}) :
                                    (${requestScope.event.eventResult.loserScore}) ${requestScope.event.eventResult.loserName}</p>
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.user.role eq 'BOOKMAKER'}">
                            <c:if test="${requestScope.event.readyToAddResult}">
                                <div class="event-result">
                                    <a class="add-result-link"
                                       href="<c:url value="main-servlet?command=to_add_event_result_page&eventId=${requestScope.event.eventId}"/>">
                                        <fmt:message key="event.addEventResult"/>
                                    </a>
                                </div>
                            </c:if>
                        </c:if>
                        <div class="members-div">
                            <h4><fmt:message key="event.members"/>:</h4>
                            <c:forEach var="member" items="${requestScope.event.members}">
                                <ul class="member">
                                    <li>${member.memberName}</li>
                                </ul>
                            </c:forEach>
                        </div>
                        <c:if test="${requestScope.event.readyToAddResult}">
                            <div class="info-div">
                                <fmt:message key="event.resultIsNotSet"/>
                            </div>
                        </c:if>
                        <c:if test="${requestScope.event.readyToBet}">
                            <a class="make-bet-link"
                               href="<c:url value="main-servlet?command=to_make_bet_page&eventId=${requestScope.event.eventId}"/>">
                                <fmt:message key="event.makeBet"/>
                            </a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--footer-->
</div>
</body>
</html>
