<%--
  Created by IntelliJ IDEA.
  User: HrynyshynRoman
  Date: 30.08.2016
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="ua.nure.hrynyshyn.resources.pageContent"/>
<html>
<head>
    <title>Buying error</title>
</head>
<body>
<%@include file="languagePanel.html"%>
<jsp:include page="profileBar.jsp"/>
<jsp:include page="navigationPanel.jsp"/>
<br>
<br>
<div align="center">
    <h3>
        <fmt:message key="ticket.errorMessage"/>
    </h3>
    <a href="buyTicket.jsp"><fmt:message key="global.back"/> </a>
</div>
</body>
</html>
