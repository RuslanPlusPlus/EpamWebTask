<%--
  Created by IntelliJ IDEA.
  User: rusne
  Date: 23.06.2021
  Time: 1:46
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
    <title>Top up balance</title>
</head>
<body>
<div class="container_s">
    <c:import url="fragments/header.jsp"/>
    <div class="main">
        <!-- left menu -->
        <div class="center-part">
            <h1><fmt:message key="balance.topUpTitle"/></h1>
            <div class="card">
                <div class="card-body">

                    <c:if test="${not empty requestScope.errorTopUpBalance}">
                        <div class="error-div">
                                ${requestScope.errorTopUpBalance}
                        </div>
                    </c:if>

                    <form method="post" action="main-servlet">
                        <input type="hidden" name="command" value="top_up_balance" />


                        <div class="form-group row">
                            <label for="cardNumber" class="col-sm-2 col-form-label">
                                <fmt:message key="balance.cardNumber"/>
                            </label>
                            <div class="col-sm-7">
                                <input required type="text" name="cardNumber" id="cardNumber" class="form-control"
                                       placeholder="XXXXXXXXXXXXXXXX" pattern="^\d{16}$">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="amount" class="col-sm-2 col-form-label">
                                <fmt:message key="balance.amountToTopUp"/>
                            </label>
                            <div class="col-sm-7">
                                <input required type="text" name="amount" id="amount" class="form-control"
                                       placeholder="XXX.XX" pattern="[0-9]{1,3}\.[0-9]{1,2}">
                            </div>
                        </div>

                        <button type="submit" class="btn btn-primary">
                            <fmt:message key="balance.topUp"/>
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <ctg:footer/>
</div>
</body>
</html>
