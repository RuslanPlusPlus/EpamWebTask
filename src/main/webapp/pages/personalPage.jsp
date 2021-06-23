<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 16.06.2021
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="user" value="${sessionScope.user}" />
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Personal page</title>
    <link href="${contextPath}/styles/personalPage.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <c:import url="fragments/header.jsp"/>

    <div class="main">
        <div class="center-part">
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
            <div class="personal-page-div">
                <div class="personal-page-header">
                    <div class="id-info-div">
                        <h3>${user.username}</h3>
                        <p class="label">${user.email}</p>
                        <p class="label">${user.role}</p>
                    </div>
                    <div class="balance-div">
                        <div class="balance-h4-div">
                            <h3><fmt:message key="person.balance"/> </h3>
                            <p class="label">${user.balance}</p>
                        </div>
                        <a class="balance-btn" href="<c:url value="main-servlet?command=to_top_up_balance_page"/>">
                            <fmt:message key="person.topUp" />
                        </a>
                    </div>
                </div>
                <div class="active-rates-div">
                    <hr>
                    <h4><fmt:message key="person.activeBets" /></h4>
                    <table>
                        <tr class="table-header">
                            <th><fmt:message key="person.event" /></th>
                            <th><fmt:message key="person.bet" /></th>
                            <th><fmt:message key="person.betType" /></th>
                        </tr>
                        <c:forEach var="bet" items="${user.activeBets}">
                            <tr>
                                <td><a href="<c:url value="main-servlet?command=to_event_page&eventId=${bet.eventId}"/>">
                                        ${bet.eventName}
                                </a></td>
                                <td>${bet.money}</td>
                                <td>${bet.type}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <hr>
                <div class="finished-rates-div">
                    <hr>
                    <h4><fmt:message key="person.completedBets" /></h4>
                    <table>
                        <tr class="table-header">
                            <th><fmt:message key="person.event" /></th>
                            <th><fmt:message key="person.bet" /></th>
                            <th><fmt:message key="person.betType" /></th>
                            <th><fmt:message key="person.winAmount" /></th>
                        </tr>
                        <c:forEach var="bet" items="${user.completedBets}">
                            <tr>
                                <td><a href="<c:url value="main-servlet?command=to_event_page&eventId=${bet.eventId}"/>">
                                        ${bet.eventName}
                                </a></td>
                                <td>${bet.money}</td>
                                <td>${bet.type}</td>
                                <td>${bet.winMoney}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <!-- footer -->
</div>
</body>
</html>
