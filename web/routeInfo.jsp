<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: GrynyshynRoman
  Date: 26.08.2016
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Route info</title>
</head>
<body>
<div align="center">
    Depart station: ${requestScope.routeInfo.departStation.name}<br>
    Depart time:
    <jsp:useBean id="departTime" class="java.util.Date"/>
    <jsp:setProperty name="departTime" property="time" value="${requestScope.routeInfo.route.departTime}"/>
    <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${departTime}"/><br>
    <c:forEach var="station" items="${requestScope.routeInfo.wayStations}" varStatus="counter">
        <br>
        Station: ${requestScope.routeInfo.stations[counter.index].name}<br>
        Arrive time :
        <jsp:useBean id="arrivalTime" class="java.util.Date"/>
        <jsp:setProperty name="arrivalTime" property="time" value="${station.arrivalTime}"/>
        <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${arrivalTime}"/><br>
        Depart time :
        <jsp:useBean id="depTime" class="java.util.Date"/>
        <jsp:setProperty name="depTime" property="time" value="${station.departTime}"/>
        <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${depTime}"/><br>
        Waiting time
        <jsp:useBean id="waitingTime" class="ua.nure.hrynyshyn.core.supportClasses.TimeFormatter"/>
        <jsp:setProperty name="waitingTime" property="total" value="${station.waitingTime}"/>
        ${waitingTime.time}<br>
        <br>

    </c:forEach>
    Destination station: ${requestScope.routeInfo.destStation.name}<br>
    Destination time:
    <jsp:useBean id="destTime" class="java.util.Date"/>
    <jsp:setProperty name="destTime" property="time" value="${requestScope.routeInfo.route.destTime}"/>
    <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${destTime}"/><br>
    <a href="searchResults.jsp">Back</a>
</div>

</body>
</html>
