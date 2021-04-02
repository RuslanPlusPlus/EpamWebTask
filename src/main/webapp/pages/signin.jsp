<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 30.03.2021
  Time: 3:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<form name="loginForm" method="post" action="main-servlet">
    <input type="hidden" name="command" value="sign_up" />
    Login:
    <br/>
    Email:
    <input type="text" name="login" value="" placeholder="enter email"/>
    <br/>
    Password:
    <input type="password" name="password" value="" placeholder="enter password"/>
    <br/>
    <%
        if(request.getAttribute("errorLoginPassMessage") != null){
            System.out.println(request.getAttribute("errorLoginPassMessage"));
        }
    %>
    <br/>
    <input type="submit" value="Log in"/>
    <a href="main-servlet?command=not_registered">Not registered?</a>
</form>
</body>
</html>
