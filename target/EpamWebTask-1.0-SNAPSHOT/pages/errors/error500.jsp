<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 25.03.2021
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error 500</title>
</head>
<body>
<h1>Error. Status code: 500</h1>
<div class="ui-state-error-text">
    Error message: ${requestScope.error}
</div>
</body>
</html>
