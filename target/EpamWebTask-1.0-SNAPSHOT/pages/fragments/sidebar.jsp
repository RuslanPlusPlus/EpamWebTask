<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 19.06.2021
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Sidebar</title>
</head>
<body>
<div class="sideBar">
    <nav>
        <b><fmt:message key="sidebar.sportKinds" /></b>
        <c:forEach var="kind" items="${requestScope.sportKinds}">
            <a class="make-bet-link" href="<c:url value="main-servlet?command=show_events_by_sport_kind&sportKindId=${kind.kindId}" />" >
                    ${kind.kindName}
            </a>
        </c:forEach>
    </nav>
</div>
</body>
</html>
