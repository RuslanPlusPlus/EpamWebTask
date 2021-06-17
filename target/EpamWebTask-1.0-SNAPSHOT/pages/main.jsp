<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 30.03.2021
  Time: 3:06
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
    <title>Main page</title>
</head>
<body>
<div class="container">
    <c:import url="fragments/header.jsp"/>
    <div class="main-part">
        <!-- left menu -->



        <!--
        <div class="center-part">
            <div>

            </div>
            <div>
                <form action="main-servlet" method="post">
                    <input type="hidden" name="command" value="find_user_by_email">
                    Email:
                    <input type="email" name="email" value="">
                    <br/>
                    <input type="submit" name="submit" value="Display user">
                </form>
            </div>
            <div>
                <p>
                    <a href="${pageContext.request.contextPath}/main-servlet?command=to_login_page">Login</a>
                </p>
            </div>
        </div>
        -->


    </div>
</div>



</body>
</html>
