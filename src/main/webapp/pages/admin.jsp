<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 16.06.2021
  Time: 21:48
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
    <title>Admin page</title>
</head>
<body>
<div class="container_s">
    <c:import url="fragments/header.jsp"/>
    <div class="main">
        <!-- left menu -->
        <div class="center-part">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-2 m-2">
                        <div class="list-group">
                            <a href="${pageContext.request.contextPath}/main-servlet?command=to_users_page"
                               class="make-bet-link">
                                <fmt:message key="admin.users"/>
                            </a>
                            <br/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <ctg:footer/>
</div>
</body>
</html>
