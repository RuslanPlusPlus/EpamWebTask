<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 01.04.2021
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Register page</title>
</head>
<body>
<div class="container_s">
    <c:import url="fragments/header.jsp"/>
    <div class="main">
        <!-- left menu -->
        <div class="center-part">
            <h1><fmt:message key="registrationForm"/></h1>
            <div class="card">
                <div class="card-body">
                    <form name="registerForm" method="post" action="main-servlet">

                        <c:if test="${not empty requestScope.errorIncorrectData}">
                            <div class="alert-warning">
                                <fmt:message key="error.register.incorrectData"/>
                            </div>
                            <c:remove var="errorIncorrectData" />
                        </c:if>

                        <input type="hidden" name="command" value="sign_up">

                        <div class="form-group row">
                            <label for="username" class="col-sm-2 col-form-label">
                                <fmt:message key="username"/>
                            </label>
                            <div class="col-sm-7">
                                <input type="text" name="username" id="username" class="form-control"
                                       placeholder="Enter username(at least 3 symbols)" pattern="^(?=.{1,40}$)[a-zA-Z]+[\w][\w -]*[\w]" required>
                            </div>
                        </div>

                        <c:if test="${not empty requestScope.errorEmailExists}">
                            <div class="alert-warning">
                                <fmt:message key="error.register.emailExists"/>
                            </div>
                            <c:remove var="errorEmailExists" />
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
                                       placeholder="Enter password(from 4 to 12 symbols)" pattern="\w{4,12}">
                            </div>
                        </div>

                        <button type="submit" class="btn btn-primary">
                            <fmt:message key="header.register"/>
                        </button>
                        <a class="nav nav-link" href="${contextPath}/main-servlet?command=to_login_page">
                            <fmt:message key="alreadyRegistered"/>
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
