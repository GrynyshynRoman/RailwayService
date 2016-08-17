<%--
  Created by IntelliJ IDEA.
  User: GrynyshynRoman
  Date: 17.08.2016
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Routes edit</title>
</head>
<body>
Routes management:
<table border="1">
    <tr>
        <td>
            Create route:<br>
            <form action="createRoute" method="post">
                Department station id: <input type="text" name="deptStationID"><br>
                Department time: <input type="date" name="deptDate"> <input type="time" name="deptTime"><br>
                Destination station id: <input type="text" name="destStationID"><br>
                Destination time: <input type="date" name="destDate"><input type="time" name="destTime"><br>
                <input type="submit" value="Create">
            </form>
        </td>
        <td>
            Edit route:<br>
            <form action="editRoute" method="post">
                Route id: <input type="text" name="routeID"><br>
                Department station id: <input type="text" name="deptStationID"><br>
                Department time: <input type="date" name="deptDate"> <input type="time" name="deptTime"><br>
                Destination station id: <input type="text" name="destStationID"><br>
                Destination time: <input type="date" name="destDate"><input type="time" name="destTime"><br>
                <input type="submit" value="Edit">
            </form>
        </td>
        <td>
            Delete route:
            <form action="deleteRoute" method="post">
                Route id: <input type="text" name="routeID"><br>
                <input type="submit" value="Delete">
            </form>
        </td>
    </tr>
    <tr>
        <td>
            Add way station:<br>
            <form action="addWayStation" method="post">
                Route ID: <input type="text" name="routeID"><br>
                Station ID: <input type="text" name="stationID"><br>
                Arrival time: <input type="date" name="arrivDate"> <input type="time" name="arrivTime"><br>
                Department time: <input type="date" name="deptDate"> <input type="time" name="deptTime"><br>
                Waiting time: <input type="time" name="waitingTime"><br>
                <input type="submit" value="Add">
            </form>
        </td>
        <td>
            Edit way station: <br>
            <form action="editWayStation" method="post">
                Way station id: <input type="text" name="wayStationID"><br>
                Route ID: <input type="text" name="routeID"><br>
                Station ID: <input type="text" name="stationID"><br>
                Arrival time: <input type="date" name="arrivDate"> <input type="time" name="arrivTime"><br>
                Department time: <input type="date" name="deptDate"> <input type="time" name="deptTime"><br>
                Waiting time: <input type="time" name="waitingTime"><br>
                <input type="submit" value="Edit">
            </form>
        </td>
        <td>
            Delete way station: <br>
            <form action="deleteWayStation" method="post">
                Way station id: <input type="text" name="wayStationID"><br>
                <input type="submit" value="Delete">
            </form>
        </td>
    </tr>
</table>
<table>
    <tr>
        <td>
            Routes:
            <table border="1">
                <tr>
                    <td>Route id</td>
                    <td>Department station id</td>
                    <td>Department time</td>
                    <td>Destination station id</td>
                    <td>Destination time</td>
                </tr>
                <c:forEach var="route" items="${routes}">
                    <tr>
                        <td>${route.route_ID}</td>
                        <td>${route.departStation_ID}</td>
                        <td>
                            <jsp:useBean id="deptTime" class="java.util.Date"/>
                            <jsp:setProperty name="deptTime" property="time" value="${route.departTime}"/>
                            <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${deptTime}"/>
                        </td>
                        <td>${route.destStation_ID}</td>
                        <td>
                            <jsp:useBean id="destTime" class="java.util.Date"/>
                            <jsp:setProperty name="destTime" property="time" value="${route.destTime}"/>
                            <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${destTime}"/>
                        </td>
                        <!--<td>
                        <form action="deleteStation" method="post">
                            <input type="text" name="id" value="${station.station_ID}" hidden>
                            <input type="submit" value="Delete">
                        </form>
                        <form action="editStation" method="post">
                            <input type="submit" value="Edit">
                        </form>
                    </td>-->
                    </tr>
                </c:forEach>
            </table>
        </td>
        <td>
            WayStation:
            <table border="1">
                <tr>
                    <td>Way station id</td>
                    <td>Route id</td>
                    <td>Station id</td>
                    <td>Arrival time</td>
                    <td>Depart time id</td>
                    <td>Waiting time</td>
                </tr>
                <c:forEach var="wayStation" items="${wayStations}">
                    <tr>
                        <td>${wayStation.wayStation_ID}</td>
                        <td>${wayStation.route_ID}</td>
                        <td>${wayStation.station_ID}</td>
                        <td>
                            <jsp:useBean id="arrivTime" class="java.util.Date"/>
                            <jsp:setProperty name="arrivTime" property="time" value="${wayStation.arrivalTime}"/>
                            <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${arrivTime}"/>
                        </td>
                        <td>
                            <jsp:useBean id="deparTime" class="java.util.Date"/>
                            <jsp:setProperty name="deparTime" property="time" value="${wayStation.departTime}"/>
                            <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${deparTime}"/>
                        </td>
                        <td>
                            <jsp:useBean id="waitTime" class="java.util.Date"/>
                            <jsp:setProperty name="waitTime" property="time" value="${wayStation.waitingTime}"/>
                            <fmt:formatDate pattern="HH:mm" value="${waitTime}"/>
                        </td>
                        <!--<td>
                        <form action="deleteStation" method="post">
                            <input type="text" name="id" value="${station.station_ID}" hidden>
                            <input type="submit" value="Delete">
                        </form>
                        <form action="editStation" method="post">
                            <input type="submit" value="Edit">
                        </form>
                    </td>-->
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>

<jsp:include page="toAdminHome.html"></jsp:include>
</body>
</html>
