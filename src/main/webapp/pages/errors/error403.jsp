<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 20.06.2021
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Error 403</title>
</head>
<body>
<h1>Error. Status code: 403</h1>
<div class="ui-state-error-text">
    Error message: ${requestScope.error}
</div>
<div>
    <h3>Maybe Login</h3>
    <a class="main-menu-item" href="<c:url value="main-servlet?command=to_login_page"/>">
        <fmt:message key="header.login"/>
    </a>
</div>
</body>
</html>
