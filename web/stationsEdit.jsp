<%--
  Created by IntelliJ IDEA.
  User: GrynyshynRoman
  Date: 09.08.2016
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Stations</title>
</head>
<body>
<table border="1">
    <tr>
        <td>
            Add station:<br>
            <form action="addStation" method="post">
                Name: <input type="text" name="name"><br>
                City: <input type="text" name="city"><br>
                State: <input type="text" name="state"><br>
                Country:<input type="text" name="country"><br>
                <input type="submit" value="Add">
            </form>
        </td>
        <td>
            Edit station:<br>
            <form action="editStation" method="post">
                ID: <input type="text" name="id"><br>
                Name: <input type="text" name="name"><br>
                City: <input type="text" name="city"><br>
                State: <input type="text" name="state"><br>
                Country:<input type="text" name="country"><br>
                <input type="submit" value="Add">
            </form>
        </td>
        <td>
            Delete station:<br>
            <form action="deleteStation" method="post">
                ID: <input type="text" name="id">
                <input type="submit" value="Delete">
            </form>
        </td>
    </tr>
</table>
<table border="1">
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>City</td>
        <td>State</td>
        <td>Country</td>
    </tr>
    <c:forEach var="station" items="${stations}">
        <tr>
            <td>${station.station_ID}</td>
            <td>${station.name}</td>
            <td>${station.city}</td>
            <td>${station.state}</td>
            <td>${station.country}</td>
            <td>
                <form action="deleteStation" method="post">
                    <input type="text" name="id" value="${station.station_ID}" hidden>
                    <input type="submit" value="Delete">
                </form>
                <form action="editStation" method="post">
                    <input type="submit" value="Edit">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="toAdminHome.html"></jsp:include>
</body>
</html>
