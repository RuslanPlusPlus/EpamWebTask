<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 21.06.2021
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Add event result</title>
</head>
<body>
<div class="container_s">
    <c:import url="fragments/header.jsp"/>
    <div class="main">
        <div class="center-part">
            <div class="form" style="width: 600px;">
                <form class="form" method="post" action="main-servlet">
                    <div class="header-div">
                        <p class="form-header"><fmt:message key="eventResult.addEventResultTitle"/></p>
                    </div>

                    <input type="hidden" name="command" value="add_event_result"/>
                    <input type="hidden" name="eventId" value="${requestScope.event.eventId}"/>

                    <div class="event-block">
                        <div class="event-header">
                            <div>
                                <h5 class="event-name"><c:out value="${requestScope.event.eventName}" /></h5>
                                <p class="event-sport_kind">${requestScope.event.sportKindName}</p>
                            </div>
                        </div>
                    </div>

                    <c:if test="${not empty sessionScope.inputIncorrectFormat}">
                        <div class="error-div">
                                ${sessionScope.inputIncorrectFormat}
                        </div>
                    </c:if>

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

                    <div class="input-div">
                        <label><fmt:message key="bet.selectWinner"/> </label>
                        <select class="custom-select" id="winnerId" name="winnerId">
                            <c:forEach items="${requestScope.event.members}" var="member">
                                <option value="${member.memberId}"> ${member.memberName} </option>
                            </c:forEach>
                            <input required name="winnerScore" type="number"
                                   placeholder="Winner score" pattern="^\d{1,4}$"/>
                        </select>
                    </div>

                    <div class="input-div">
                        <label><fmt:message key="bet.selectLoser"/> </label>
                            <select class="custom-select" id="loserId" name="loserId">
                                <c:forEach items="${requestScope.event.members}" var="member">
                                    <option value="${member.memberId}"> ${member.memberName} </option>
                                </c:forEach>
                            </select>
                            <input required name="loserScore" type="number"
                                   placeholder="Loser score" pattern="^\d{1,4}$"/>
                    </div>

                    <div>
                        <button  class="make-bet-link" type="submit">
                            <fmt:message key="eventResult.addResult"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <ctg:footer/>
</div>
</body>
</html>
