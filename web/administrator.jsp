<%--
  Created by IntelliJ IDEA.
  User: GrynyshynRoman
  Date: 12.08.2016
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Administrator</title>
</head>
<body>
Add station:<br>
<form action="addStation" method="post">
    Name: <input type="text" name="name"><br>
    City: <input type="text" name="city"><br>
    State: <input type="text" name="state"><br>
    Country:<input type="text" name="country"><br>
    <input type="submit" name="Add">
</form>
Delete station:<br>
<form action="deleteStation" method="post">
    ID: <input type="text" name="id">
    <input type="submit" name="Delete">
</form>
<div>
    Stations:
    <table border="1" align=center>
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
</div>
</body>
</html>
