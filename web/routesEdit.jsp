<%--
  Created by IntelliJ IDEA.
  User: GrynyshynRoman
  Date: 17.08.2016
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="ua.nure.hrynyshyn.resources.pageContent"/>
<html>
<head>
    <title>Routes edit</title>
    <link rel="stylesheet" href="test.css">
</head>
<body>
<%@include file="languagePanel.html"%>
<%@include file="adminPanel.jsp" %>
<table align="center" width="90%" border="1">
    <tr>
        <td>
            <fmt:message key="route.createRoute"/> :<br>
            <form action="createRoute" method="post">
                <div>
                    <label><fmt:message key="route.depStatID"/>:</label><input type="text" name="deptStationID"><br>
                    <label><fmt:message key="route.deptTime"/>:</label><input type="date" name="deptDate"> <input type="time"
                                                                                              name="deptTime"><br>
                    <label><fmt:message key="route.destStatID"/>:</label><input type="text" name="destStationID"><br>
                    <label><fmt:message key="route.destTime"/>:</label><input type="date" name="destDate"><input type="time"
                                                                                              name="destTime"><br>
                    <label></label><input type="submit" value="<fmt:message key="route.create"/>">
                </div>
            </form>
        </td>
        <td>
            <fmt:message key="route.editRoute"/> :<br>
            <form action="editRoute" method="post">
                <div>
                    <label><fmt:message key="route.routeID"/> :</label><input type="text" name="routeID"><br>
                    <label><fmt:message key="route.depStatID"/>:</label><input type="text" name="deptStationID"><br>
                    <label><fmt:message key="route.deptTime"/>:</label><input type="date" name="deptDate"> <input type="time"
                                                                                                                  name="deptTime"><br>
                    <label><fmt:message key="route.destStatID"/>:</label><input type="text" name="destStationID"><br>
                    <label><fmt:message key="route.destTime"/>:</label><input type="date" name="destDate"><input type="time"
                                                                                                                 name="destTime"><br>
                    <label></label><input type="submit" value="<fmt:message key="admin.edit"/> ">
                </div>
            </form>
        </td>
        <td>
            <fmt:message key="route.deleteRoute"/> :
            <form action="deleteRoute" method="post">
                <div>
                    <label><fmt:message key="route.routeID"/>:</label><input type="text" name="routeID">
                    <label></label><input type="submit" value="<fmt:message key="admin.delete"/>"></div>
            </form>
        </td>
    </tr>
</table>
<br>
<br>
<table align="center" width="90%" border="1">
    <tr>
        <td>
            <fmt:message key="ws.addWS"/> :<br>
            <form action="addWayStation" method="post">
                <div>
                    <label><fmt:message key="ws.routeid"/> :</label> <input type="text" name="routeID"><br>
                    <label><fmt:message key="ws.stationid"/>:</label> <input type="text" name="stationID"><br>
                    <label><fmt:message key="ws.arrivalTime"/>:</label> <input type="date" name="arrivDate"> <input type="time"
                                                                                             name="arrivTime"><br>
                    <label><fmt:message key="ws.depTime"/>:</label> <input type="date" name="deptDate"> <input type="time"
                                                                                               name="deptTime"><br>
                    <label><fmt:message key="ws.waitTime"/>:</label> <input type="time" name="waitingTime"><br>
                    <label></label><input type="submit" value="<fmt:message key="admin.add"/>">
                </div>
            </form>
        </td>
        <td>
            <fmt:message key="ws.editWS"/>: <br>
            <form action="editWayStation" method="post">
                <div>
                    <label><fmt:message key="ws.wsID"/> :</label> <input type="text" name="wayStationID"><br>
                    <label><fmt:message key="ws.routeid"/> :</label> <input type="text" name="routeID"><br>
                    <label><fmt:message key="ws.stationid"/>:</label> <input type="text" name="stationID"><br>
                    <label><fmt:message key="ws.arrivalTime"/>:</label> <input type="date" name="arrivDate"> <input type="time"
                                                                                                                    name="arrivTime"><br>
                    <label><fmt:message key="ws.depTime"/>:</label> <input type="date" name="deptDate"> <input type="time"
                                                                                                               name="deptTime"><br>
                    <label><fmt:message key="ws.waitTime"/>:</label> <input type="time" name="waitingTime"><br>
                    <label></label><input type="submit" value="<fmt:message key="admin.edit"/>">
                </div>
            </form>
        </td>
        <td>
            <fmt:message key="ws.deleteWS"/>: <br>
            <form action="deleteWayStation" method="post">
                <div>
                    <label><fmt:message key="ws.wsID"/>:</label> <input type="text" name="wayStationID"><br>
                    <label></label><input type="submit" value="<fmt:message key="admin.delete"/>">
                </div>
            </form>
        </td>
    </tr>
</table>
<table align="center" width="90%" >
    <tr>
        <td>
            <fmt:message key="route.routes"/>:
            <table border="1">
                <tr>
                    <td><fmt:message key="route.routeID"/></td>
                    <td><fmt:message key="route.depStatID"/></td>
                    <td><fmt:message key="route.deptTime"/></td>
                    <td><fmt:message key="route.destStatID"/></td>
                    <td><fmt:message key="route.destTime"/></td>
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
                    </tr>
                </c:forEach>
            </table>
        </td>
        <td>
            <fmt:message key="ws.wayStations"/>:
            <table border="1">
                <tr>
                    <td><fmt:message key="ws.wsID"/></td>
                    <td><fmt:message key="route.routeID"/></td>
                    <td><fmt:message key="ws.stationid"/></td>
                    <td><fmt:message key="ws.arrivalTime"/></td>
                    <td><fmt:message key="routeInfo.departTime"/></td>
                    <td><fmt:message key="ws.waitTime"/></td>
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
                            <jsp:useBean id="waitingTime" class="ua.nure.hrynyshyn.core.supportClasses.TimeFormatter"/>
                            <jsp:setProperty name="waitingTime" property="total" value="${wayStation.waitingTime}"/>
                                ${waitingTime.time}
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>

</body>
</html>
