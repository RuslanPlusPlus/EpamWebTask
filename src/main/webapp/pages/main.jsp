<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 30.03.2021
  Time: 3:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>
<div>
    <form action="main-servlet" method="get">
        <input type="hidden" name="command" value="find_all_users">
        <input type="submit" name="submit" value="Display user list">
    </form>
</div>
<div>
    <form action="main-servlet" method="post">
        <input type="hidden" name="command" value="find_user_by_email">
        Email:
        <input type="email" name="email" value="">
        <br/>
        <input type="submit" name="submit" value="Display user">
    </form>
</div>
</body>
</html>
