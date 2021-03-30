<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Epam task</h1>
<br/>
<div>
    <form action="main-servlet" method="get">
        <input type="hidden" name="command" value="display_user_list">
        <input type="submit" name="submit" value="Display user list">
    </form>
</div>
<br/>
</body>
</html>