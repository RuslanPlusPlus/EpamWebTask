<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 30.03.2021
  Time: 3:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<form name="loginForm" method="post" action="main-servlet">
    <input type="hidden" name="command" value="sign_in" />
    <c:if test="${login_error != null}">
        ${login_error}
        <c:remove var="login_error" />
        <br/>
    </c:if>
    Login:
    <br/>
    Email:
    <input type="text" name="email" value="" placeholder="enter email"/>
    <br/>
    Password:
    <input type="password" name="password" value="" placeholder="enter password"/>
    <br/>
    <input type="submit" value="Login"/>
    <a href="main-servlet?command=to_sign_up">Not registered?</a>
</form>
</body>
</html>
