<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 30.03.2021
  Time: 3:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>

<div>
    <form action="main-servlet" method="get">
        <input type="hidden" name="command" value="display_user_list">
        <input type="submit" name="submit" value="Display user list">
    </form>
</div>

</body>
</html>
