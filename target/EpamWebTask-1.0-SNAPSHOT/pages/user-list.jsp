<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 22.03.2021
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>list</title>
</head>
<body>
<h1>User list</h1>
<div>
    <c:if test="${errorNoUsers != null}">
        ${errorNoUsers}
        <c:remove var="errorMessage" />
    </c:if>
    <table>
        <c:forEach var="item" items="${requestScope.users}" varStatus="status">
            <tr>
                <td><c:out value="${item.username}"></c:out></td>
                <td><c:out value="${item.email}"></c:out></td>
                <td><c:out value="${item.role}"></c:out></td>
                <td><c:out value="${item.balance}"></c:out></td>
                <td><c:out value="${item.email}"></c:out></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>


