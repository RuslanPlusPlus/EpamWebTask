<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Epam task</h1>
<br/>
<jsp:forward page="main-servlet">
    <jsp:param name="command" value="to_main_page"/>
</jsp:forward>
<br/>
</body>
</html>