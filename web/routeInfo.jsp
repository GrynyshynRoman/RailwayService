<%--
  Created by IntelliJ IDEA.
  User: GrynyshynRoman
  Date: 26.08.2016
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="ua.nure.hrynyshyn.resources.pageContent"/>
<html>
<head>
    <title>Route info</title>
</head>
<body>
<%@include file="languagePanel.html" %>
<table align="center" width="50%" border="1">
    <tr>
        <td><fmt:message key="routeInfo.station"/></td>
        <td><fmt:message key="routeInfo.arriveTime"/></td>
        <td><fmt:message key="routeInfo.departTime"/></td>
        <td><fmt:message key="routeInfo.waitingTime"/></td>
    </tr>
    <tr>
        <td>${requestScope.routeInfo.departStation.name}</td>
        <td></td>
        <td>
            <jsp:useBean id="departTime" class="java.util.Date"/>
            <jsp:setProperty name="departTime" property="time" value="${requestScope.routeInfo.route.departTime}"/>
            <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${departTime}"/>


        </td>
        <td></td>
    </tr>
    <c:forEach var="station" items="${requestScope.routeInfo.wayStations}" varStatus="counter">
        <tr>
            <td>${requestScope.routeInfo.stations[counter.index].name}</td>
            <td>
                <jsp:useBean id="arrivalTime" class="java.util.Date"/>
                <jsp:setProperty name="arrivalTime" property="time" value="${station.arrivalTime}"/>
                <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${arrivalTime}"/>
            </td>
            <td>
                <jsp:useBean id="depTime" class="java.util.Date"/>
                <jsp:setProperty name="depTime" property="time" value="${station.departTime}"/>
                <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${depTime}"/>
            </td>
            <td>
                <jsp:useBean id="waitingTime" class="ua.nure.hrynyshyn.core.supportClasses.TimeFormatter"/>
                <jsp:setProperty name="waitingTime" property="total" value="${station.waitingTime}"/>
                    ${waitingTime.time}
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td>${requestScope.routeInfo.destStation.name}</td>
        <td>
            <jsp:useBean id="destTime" class="java.util.Date"/>
            <jsp:setProperty name="destTime" property="time" value="${requestScope.routeInfo.route.destTime}"/>
            <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${destTime}"/>
        </td>
        <td></td>
        <td></td>
    </tr>
</table>
</body>
</html>
