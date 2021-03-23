<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 23.03.2021
  Time: 2:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sorted list</title>
</head>
<body>
<h1>Sorted user list by age predicate</h1>
<div>
    <table>
        <c:forEach var="item" items="${list}" varStatus="status">
            <tr>
                <td><c:out value="${item.name}"></c:out></td>
                <td><c:out value="${item.age}"></c:out></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
