<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 30.03.2021
  Time: 3:06
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
    <title>Main page</title>
</head>
<body>
<div class="container_s">
    <c:import url="fragments/header.jsp"/>
    <div class="main">
        <div class="center-part"
        <c:import url="fragments/sidebar.jsp"/>
            <c:forEach var="event" items="${requestScope.events}">
                <div class="event">
                    <div class="event-main">
                        <h5 class="event-name"><c:out value="${event.eventName}" /></h5>
                        <p class="event-sport_kind">${event.sportKindName}</p>
                        <time class="event-date">${event.date} ${event.time}</time>
                    </div>
                    <div class="event-secondary">
                        <a href="<c:url value="main-servlet?command=to_event_page&eventId=${event.eventId}" />">
                            <button class="event-btn"><fmt:message key="main.event.btn" /></button>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

</body>
</html>
