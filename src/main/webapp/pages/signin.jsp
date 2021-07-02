<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 30.03.2021
  Time: 3:05
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
    <title>Login page</title>
</head>
<body>

<div class="container_s">
    <c:import url="fragments/header.jsp"/>
    <div class="main">
        <!-- left menu -->
        <div class="center-part">
            <h1><fmt:message key="loginForm"/></h1>
            <div class="card">
                <div class="card-body">
                    <form name="loginForm" method="post" action="main-servlet">
                        <input type="hidden" name="command" value="sign_in" />

                        <c:if test="${requestScope.login_error != null}">
                            <div class="alert-warning">
                                <fmt:message key="error.login.incorrectEmailOrPassword"/>
                                <c:remove var="login_error" />
                            </div>
                        </c:if>

                        <div class="form-group row">
                            <label for="email" class="col-sm-2 col-form-label">
                                <fmt:message key="email"/>
                            </label>
                            <div class="col-sm-7">
                                <input required type="email" name="email" id="email" class="form-control"
                                       placeholder="Enter email">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="password" class="col-sm-2 col-form-label">
                                <fmt:message key="password"/>
                            </label>
                            <div class="col-sm-7">
                                <input required type="password" name="password" id="password" class="form-control"
                                       placeholder="Enter password" pattern="\w{4,12}">
                            </div>
                        </div>

                        <button type="submit" class="btn btn-primary">
                            <fmt:message key="header.login"/>
                        </button>
                        <a href="${pageContext.request.contextPath}/main-servlet?command=to_register_page">
                            <fmt:message key="notRegistered"/>
                        </a>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <ctg:footer/>
</div>
</body>
</html>
