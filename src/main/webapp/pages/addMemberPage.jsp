<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 22.06.2021
  Time: 18:14
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
    <title>Add member</title>
</head>
<body>
<div class="container">
    <c:import url="fragments/header.jsp"/>
    <div class="main">
        <div class="center-part">
            <div class="form" style="width: 600px;">
                <form class="form" method="post" action="main-servlet" >

                    <input type="hidden" name="command" value="add_member"/>

                    <div class="header-div">
                        <p class="form-header"><fmt:message key="member.addMember"/></p>
                    </div>

                    <div class="input-div">
                        <label for="memberName"><fmt:message key="member.memberName" /></label>
                        <input required id="memberName" type="text" name="memberName"
                               placeholder="<fmt:message key="sportKind.sportKind"/> "/>
                    </div>

                    <div class="input-div">
                        <label><fmt:message key="event.selectSportKind" /></label>
                        <select id="category-select" name="sportKindId" >
                            <c:forEach var="sportKind" items="${requestScope.sportKinds}">
                                <option value="${sportKind.kindId}">${sportKind.kindName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div>
                        <button class="make-bet-link" type="submit"><fmt:message key="member.addMember" /></button>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>
</body>
</html>
