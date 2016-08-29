<%--
  Created by IntelliJ IDEA.
  User: GrynyshynRoman
  Date: 03.08.2016
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="ua.nure.hrynyshyn.resources.pageContent"/>
<html lang="${applicationScope.language}">
<head>
    <title>Railway</title>
</head>
<body>
<%@include file="languagePanel.html" %>
<h1 align=center> <fmt:message key="main.title"/> </h1>
<c:if test="${sessionScope.role.role eq 'admin'}">
    <%@include file="adminPanel.jsp" %>
</c:if>
<div align=center>
    <table border="1">
        <tr>
            <td>
                <fmt:message key="search.search"/>
                <form action="search" method="get">
                    <fmt:message key="search.from"/>: <input type="text" id="from" name="departStation">
                    <fmt:message key="search.to"/>: <input type="text" id="to" name="destStation"><br>
                    <fmt:message key="search.date"/>:<input type="date" id="date" name="date">
                    <input type="submit" value=<fmt:message key="search.submit"/>>
                </form>
            </td>
            <td>
                <jsp:include page="profileBar.jsp"/>
            </td>
        </tr>
    </table>

</div>
<div align=center>

</div>
<div align=center>

</div>
</body>
</html>
