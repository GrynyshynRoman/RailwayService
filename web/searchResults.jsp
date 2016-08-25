<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: GrynyshynRoman
  Date: 25.08.2016
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SearchResults</title>
</head>
<body>
<table border="1">
    <tr>
        <td>Train number</td>
        <td>Depart station and time</td>
        <td>Time in travel</td>
        <td>Destination station and time</td>
        <td>Carriages</td>
        <td>Price</td>
        <td>Route info</td>
    </tr>
    <c:forEach var="result" items="${sessionScope.searchResults}">
        <tr>
            <td><c:out value="${result.train_ID}"/></td>
            <td>
                    ${result.departStation.name}<br>
                <jsp:useBean id="departTime" class="java.util.Date"/>
                <jsp:setProperty name="departTime" property="time" value="${result.departTime}"/>
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${departTime}"/>
            </td>
            <td>
                <jsp:useBean id="wayTime" class="ua.nure.hrynyshyn.core.supportClasses.TimeFormatter"/>
                <jsp:setProperty name="wayTime" property="total" value="${result.wayTime}"/>
                ${wayTime.time}
            </td>
            <td>
                    ${result.destStation.name}<br>
                <jsp:useBean id="destTime" class="java.util.Date"/>
                <jsp:setProperty name="destTime" property="time" value="${result.destTime}"/>
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${destTime}"/>
            </td>
            <td>
                <table border="1">
                    <tr>
                        <td>Carriage number</td>
                        <td>Type</td>
                        <td>Free places</td>
                    </tr>
                    <c:forEach var="carriage" items="${result.carriages}">
                        <tr>
                            <td> ${carriage.carriageNumber}</td>
                            <td>${carriage.type}</td>
                            <td>${carriage.totalSeats-carriage.reservedSeats}</td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
            <td>${result.price}</td>
            <td><a href="routeInfo.html">Info</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
