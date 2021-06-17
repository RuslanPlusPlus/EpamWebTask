<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 12.06.2021
  Time: 20:13
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
    <title>Header</title>
    <link href="${contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/styles/common.css" rel="stylesheet">
</head>
<body>
<header>

    <div class="logo-panel">
        <div>
            <img class="logo" src="<c:url value="/images/logo.png" />" alt="logo" />
        </div>
        <div class="locale">
            <div class="content">
                <div class="item r-t">
                    <a href="<c:url value="main-servlet?command=change_locale&language=ru" />">
                        <img src="<c:url value="/images/ru.png" />">
                    </a>
                </div>
                <div class="item l-t">
                    <a href="<c:url value="main-servlet?command=change_locale&language=en" />">
                        <img src="<c:url value="/images/en.png"/>">
                    </a>
                </div>
            </div>
        </div>
    </div>

    <div class="title">
        <h1>SPORT BET</h1>
    </div>

    <div class="menu">
        <nav>
            <div class="main-menu-item-container">
                <c:if test="${!empty sessionScope.user}">
                    <a class="main-menu-item" href="<c:url value="main-servlet?command=to_main_page"/>">
                        <fmt:message key="header.mainPage"/>
                    </a>
                    <a class="main-menu-item" href="<c:url value="main-servlet?command=to_personal_page"/>">
                        <fmt:message key="header.personalPage"/>
                    </a>
                </c:if>

                <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                    <a class="main-menu-item" href="<c:url value="main-servlet?command=to_admin_page"/>">
                        <fmt:message key="header.adminPage"/>
                    </a>
                </c:if>

                <c:if test="${sessionScope.user.role eq 'BOOKMAKER'}">
                    <a class="main-menu-item" href="<c:url value="main-servlet?command=to_bookmaker_page"/>">
                        <fmt:message key="header.bookmakerPage"/>
                    </a>
                </c:if>

                <c:if test="${empty sessionScope.user}">
                    <a class="main-menu-item" href="<c:url value="main-servlet?command=to_login_page"/>">
                        <fmt:message key="header.login"/>
                    </a>
                    <a class="main-menu-item" href="<c:url value="main-servlet?command=to_register_page"/>">
                        <fmt:message key="header.register"/>
                    </a>
                </c:if>
            </div>

            <div class="logout-menu-item-container">
                <c:if test="${!empty sessionScope.user}">
                    <a class="main-menu-item" href="<c:url value="main-servlet?command=logout"/>">
                        <fmt:message key="header.logout"/> (${sessionScope.user.username})
                    </a>
                </c:if>
            </div>
        </nav>
    </div>

</header>


</body>
</html>
