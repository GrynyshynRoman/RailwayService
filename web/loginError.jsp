<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="ua.nure.hrynyshyn.resources.pageContent"/>
<head>
    <meta charset="UTF-8">
    <title>LoginError</title>
</head>
<body>
<div align="center">
    <h3><fmt:message key="authorization.fail"/> </h3>
    <a href="index.jsp"><fmt:message key="global.back"/> </a>
</div>
</body>
</html>