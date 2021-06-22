<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 21.06.2021
  Time: 22:51
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
    <title>BookmakerPage</title>
</head>
<body>
<div class="container_s">
    <c:import url="fragments/header.jsp"/>
    <div class="main">
        <!-- left menu -->
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

            <div class="container-fluid">
                <div class="row">
                    <div class="col-2 m-2">
                        <div class="list-group">
                            <a href="${pageContext.request.contextPath}/main-servlet?command=to_add_event_page"
                               class="make-bet-link">
                                <fmt:message key="bookmaker.addEvent"/>
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
