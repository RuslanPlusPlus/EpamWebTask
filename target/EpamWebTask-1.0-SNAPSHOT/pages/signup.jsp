<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 01.04.2021
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Register page</title>
    <jsp:include page="fragments/header.jsp"/>
</head>
<body>

<div class="container">
    <h1>Registration form</h1>
    <div class="card">
        <div class="card-body">
            <form name="registerForm" method="post" action="main-servlet">
                <c:if test="${not empty email_exists}">
                    <p class="alert-warning">${email_exists}</p>
                    <c:remove var="email_exists" />
                </c:if>
                <c:if test="${not empty invalid_data}">
                    <p class="alert-warning">${invalid_data}</p>
                    <c:remove var="invalid_data" />
                </c:if>

                <input type="hidden" name="command" value="sign_up">

                <div class="form-group row">
                    <label for="username" class="col-sm-2 col-form-label">
                        Username
                    </label>
                    <div class="col-sm-7">
                        <input type="text" name="username" id="username" class="form-control"
                               placeholder="Enter username">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="email" class="col-sm-2 col-form-label">
                        Email
                    </label>
                    <div class="col-sm-7">
                        <input type="email" name="email" id="email" class="form-control"
                               placeholder="Enter email">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="password" class="col-sm-2 col-form-label">
                        Password
                    </label>
                    <div class="col-sm-7">
                        <input type="password" name="password" id="password" class="form-control"
                               placeholder="Enter password">
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Register</button>
                <a class="nav nav-link" href="${contextPath}/main-servlet?command=to_login_page">Already registered? Login</a>
            </form>
        </div>
    </div>
</div>

<!--
<form name="registerForm" method="post" action="main-servlet">
    <input type="hidden" name="command" value="sign_up" />
    Registration
    <br/>
    Username:
    <input type="text" name="username" value="" placeholder="enter username"/>
    <br/>
    Email:
    <input type="email" name="email" value="" placeholder="enter email"/>
    <br/>
    Password:
    <input type="password" name="password" value="" placeholder="enter password"/>
    <br/>
    <br/>
    <input type="submit" value="Register"/>
    <a href="${pageContext.request.contextPath}/main-servlet?command=to_login_page">Already registered? Login</a>
</form>
-->
</body>
</html>
