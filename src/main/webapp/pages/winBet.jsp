<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 20.06.2021
  Time: 14:21
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
<div class="container">
    <c:import url="fragments/header.jsp"/>
    <div class="main">
        <div class="center-part">
            <div class="form" style="width: 600px;">
                <form class="form" method="post" action="main-servlet">
                    <div class="header-div">
                        <p class="form-header"><fmt:message key="bet.winBet"/></p>
                    </div>

                    <input type="hidden" name="command" value="make_win_bet"/>
                    <input type="hidden" name="eventId" value="${requestScope.event.eventId}"/>
                    <input type="hidden" name="userId" value="${sessionScope.user.userId}"/>

                    <div class="event-block">
                        <div class="event-header">
                            <h5 class="event-name"><c:out value="${requestScope.event.eventName}" /></h5>
                            <p class="event-sport_kind">${requestScope.event.sportKindName}</p>
                        </div>
                    </div>

                    <div class="input-group">
                        <label><fmt:message key="bet.selectWinner"/> /label>
                        <select class="custom-select" id="memberId" name="memberId">
                            <c:forEach items="${requestScope.event.members}" var="member">
                                <option value="${member.memberId}"> ${member.memberName} </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="input-group">
                        <label><fmt:message key="bet.betValue" /></label>
                        <input placeholder="XXX.XX" type="text" name="money" pattern="[0-9]{1,3}\.[0-9]{1,2}"/>
                    </div>

                    <div>
                        <button  class="make-bet-link" type="submit">
                            <fmt:message key="event.makeBet"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
