<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 22.06.2021
  Time: 16:08
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
    <title>Add sport kind</title>
</head>
<body>
<div class="container">
    <c:import url="fragments/header.jsp"/>
    <div class="main">
        <div class="center-part">
            <div class="form" style="width: 600px;">
                <form class="form" method="post" action="main-servlet" >

                    <input type="hidden" name="command" value="add_sport_kind"/>

                    <div class="header-div">
                        <p class="form-header"><fmt:message key="sportKind.addSportKind"/></p>
                    </div>

                    <div class="input-div">
                        <label for="kindName"><fmt:message key="sportKind.sportKind" /></label>
                            <input required id="kindName" type="text" name="kindName"
                               placeholder="<fmt:message key="sportKind.sportKind"/> "/>
                    </div>

                    <div>
                        <button class="make-bet-link" type="submit"><fmt:message key="sportKind.addSportKind" /></button>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>
</body>
</html>
