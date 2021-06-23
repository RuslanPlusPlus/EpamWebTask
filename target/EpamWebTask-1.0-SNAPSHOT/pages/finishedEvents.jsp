<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 22.06.2021
  Time: 19:06
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
    <title>FinishedEvents</title>
</head>
<body>
<div class="container_s">
    <c:import url="fragments/header.jsp"/>
    <div class="main">
        <!-- left menu -->
        <div class="center-part">

            <c:if test="${not empty requestScope.noFinishedEvents}">
                <div class="info-div">
                    <fmt:message key="event.noFinishedEvents"/>
                </div>
            </c:if>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="event.eventName"/></th>
                    <th scope="col"><fmt:message key="sportKind.sportKind"/></th>
                    <th scope="col"><fmt:message key="bookmaker.reference"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.events}" var="event">
                    <tr>
                        <td>${event.eventName}</td>
                        <td>${event.sportKindName}</td>
                        <td>
                            <a class="make-bet-link"
                               href="<c:url value="main-servlet?command=to_add_event_result_page&eventId=${event.eventId}"/>">
                                <fmt:message key="eventResult.addResult"/>
                            </a>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
