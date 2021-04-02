<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 01.04.2021
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register page</title>
</head>
<body>
<form name="loginForm" method="post" action="main-servlet">
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
</form>
</body>
</html>
