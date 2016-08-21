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
    <link rel="stylesheet" href="test.css">
</head>
<body>
<jsp:include page="adminPanel.html"></jsp:include>
<br>
<br>
<table align="center" width="80%">
    <tr>
        <td>
            Add station:<br>
            <form action="addStation" method="post">
                <div>
                    <label>Name:</label><input type="text" name="name">
                    <label>City:</label> <input type="text" name="city">
                    <label>State:</label> <input type="text" name="state">
                    <label>Country:</label><input type="text" name="country">
                    <label></label><input type="submit" value="Add">
                </div>
                <br>


            </form>
        </td>
        <td>
            Edit station:<br>
            <form action="editStation" method="post">
                <div>
                    <label>ID:</label> <input type="text" name="id">
                    <label>Name:</label><input type="text" name="name">
                    <label>City:</label> <input type="text" name="city">
                    <label>State:</label> <input type="text" name="state">
                    <label>Country:</label><input type="text" name="country">
                    <label></label><input type="submit" value="Add">
                </div>
                <br>

            </form>
        </td>
        <td>
            Delete station:<br>
            <form action="deleteStation" method="post">
                <div>
                    <label>ID:</label> <input type="text" name="id">
                    <label></label><input type="submit" value="Delete">
                </div>
            </form>
        </td>
    </tr>
</table>
<br>
<br>
<h3 align="center">Stations</h3>
<br>

<table border="1" align="center" width="80%">
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
        </tr>
    </c:forEach>
</table>
<jsp:include page="toAdminHome.html"></jsp:include>
</body>
</html>
