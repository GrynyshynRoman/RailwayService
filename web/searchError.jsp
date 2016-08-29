<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="ua.nure.hrynyshyn.resources.pageContent"/>
<html lang="${applicationScope.language}">
<head>
    <meta charset="UTF-8">
    <title>SearchError</title>
</head>
<body>
<div align="center">
    <h3><fmt:message key="search.error"/> </h3>
    <a href="index.jsp"><fmt:message key="global.back"/> </a>
</div>
</body>
</html>