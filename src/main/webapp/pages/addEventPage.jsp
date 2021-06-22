<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 21.06.2021
  Time: 23:55
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
    <title>Add event page</title>
</head>
<body>
<div class="container">
    <c:import url="fragments/header.jsp"/>
    <div class="main">
        <div class="center-part">
            <div class="form" style="width: 600px;">
                <form class="form" method="post" action="main-servlet" >

                    <input type="hidden" name="command" value="add_event"/>

                    <div class="header-div">
                        <p class="form-header"><fmt:message key="event.addEvent"/></p>
                    </div>

                    <div class="input-div">
                        <label><fmt:message key="event.eventName" /></label>
                        <input required type="text" name="eventName" />
                    </div>

                    <div class="input-div">
                        <label><fmt:message key="event.selectSportKind" /></label>
                        <select id="category-select" name="sportKindId" >
                            <c:forEach var="sportKind" items="${requestScope.sportKinds}">
                                <option value="${sportKind.kindId}">${sportKind.kindName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="input-div">
                        <label><fmt:message key="event.dateAndTime" /></label>
                        <input required type="datetime-local" id="datetime" name="datetime"/>
                    </div>


                    <div class="input-div">
                        <label><fmt:message key="event.members" /></label>
                        <div class="member-div">
                            <select id="member1-select" name="member1Id" >
                                <c:forEach var="member" items="${requestScope.members}">
                                    <option value="${member.memberId}">${member.memberName}/${member.kindName}</option>
                                </c:forEach>
                            </select>
                            <select id="member2-select" name="member2Id" >
                                <c:forEach var="member" items="${requestScope.members}">
                                    <option value="${member.memberId}">${member.memberName}/${member.kindName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div>
                        <button class="make-bet-link" type="submit"><fmt:message key="event.addEvent" /></button>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>
</body>
</html>
