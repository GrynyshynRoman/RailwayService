<%--
  Created by IntelliJ IDEA.
  User: GrynyshynRoman
  Date: 29.08.2016
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isErrorPage="true" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="ua.nure.hrynyshyn.resources.pageContent"/>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
<div align="center">
    <fmt:message key="global.errorMessage"/><br>
    <a href="${header.referer}"><fmt:message key="global.back"/> </a>
</div>
</body>
</html>
