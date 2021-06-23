<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 17.06.2021
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Users page</title>
</head>
<body>
<div class="container_s">
    <c:import url="fragments/header.jsp"/>
    <div class="main">
        <!-- left menu -->
        <div class="center-part">
            <c:if test="${not empty requestScope.error}">
                <div class="error-div">
                        ${requestScope.error}
                </div>
            </c:if>

            <c:if test="${not empty requestScope.success}">
                <div class="success-div">
                        ${requestScope.success}
                </div>
            </c:if>
            <table class="table table-striped">

                <thead>
                <tr>
                    <th scope="col"><fmt:message key="username"/></th>
                    <th scope="col"><fmt:message key="email"/></th>
                    <th scope="col"><fmt:message key="role"/></th>
                    <th scope="col"><fmt:message key="selectRole"/></th>
                    <th scope="col"><fmt:message key="edit"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.users}" var="user">
                    <form>
                        <input type="hidden" name="command" value="change_role"/>
                        <input type="hidden" name="email" value="${user.email}"/>
                        <tr>
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>${user.role}</td>
                            <td>
                                <select class="custom-select" id="role" name="role">
                                <c:if test="${user.role.ordinal() == 2}">
                                    <option value="BOOKMAKER">BOOKMAKER</option>
                                    <option value="CLIENT">CLIENT</option>
                                </c:if>
                                <c:if test="${user.role.ordinal() == 3}">
                                    <option value="ADMIN">ADMIN</option>
                                    <option value="CLIENT">CLIENT</option>
                                </c:if>
                                <c:if test="${user.role.ordinal() == 1}">
                                    <option value="ADMIN">ADMIN</option>
                                    <option value="BOOKMAKER">BOOKMAKER</option>
                                </c:if>
                                </select>
                            </td>
                            <td>
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="edit"/>
                                </button>
                            </td>
                        </tr>
                    </form>

                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
    <ctg:footer/>
</div>
</body>
</html>
