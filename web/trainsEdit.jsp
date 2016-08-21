<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: GrynyshynRoman
  Date: 18.08.2016
  Time: 00:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trains edit</title>
    <link rel="stylesheet" href="test.css">
</head>
<body>
<jsp:include page="adminPanel.html"></jsp:include>
<table align="center" width="100%">
    <tr>
        <td>
            Add train:<br>
            <form action="addTrain" method="post">
                <div>
                    <label>Route id: </label><input type="text" name="route_ID">
                    <label></label><input type="submit" value="Add">
                </div>
            </form>
        </td>
        <td>
            Edit train:<br>
            <form action="editTrain" method="post">
                <label>ID:</label> <input type="text" name="train_ID">
                <label>Route id:</label> <input type="text" name="route_ID">
                <label></label><input type="submit" value="Edit">
            </form>
        </td>
        <td>
            Delete train:<br>
            <form action="deleteTrain" method="post">
                <label>ID:</label> <input type="text" name="train_ID">
                <label></label><input type="submit" value="Delete">
            </form>
        </td>
    </tr>
    </table>
<table align="center" width="100%">
    <tr>
        <td>
            Add carriage:<br>
            <form action="addCarriage" method="post">
                <label>Train </label> <input type="text" name="train_ID">
                <label>Carriage number:</label> <input type="text" name="carriageNumber">
                <label>Type:</label>
                <select name="type">
                    <option value="common">Common</option>
                    <option value="reservedSeat">Reserved seat</option>
                    <option value="coupe">Coupe</option>
                </select>
                <label>Total seats:</label> <input type="text" name="totalSeats">
                <label>Reserved seats:</label> <input type="text" name="reservedSeats">
                <label></label> <input type="submit" value="Add">
            </form>
        </td>
        <td>
            Edit carriage:<br>
            <form action="editCarriage" method="post">
                <label> Carriage id:</label> <input type="text" name="carriage_ID">
                <label> Train id:</label> <input type="text" name="train_ID">
                <label> Carriage number: </label><input type="text" name="carriageNumber">
                <label> Type:</label>
                <select name="type">
                    <option value="common">Common</option>
                    <option value="reservedSeat">Reserved seat</option>
                    <option value="coupe">Coupe</option>
                </select>
                <label> Total seats:</label> <input type="text" name="totalSeats">
                <label> Reserved seats:</label> <input type="text" name="reservedSeats">
                <label></label> <input type="submit" value="Edit">
            </form>
        </td>
        <td>
            Delete carriage:<br>
            <form action="deleteCarriage" method="post">
                <label> Carriage id:</label> <input type="text" name="carriage_ID">
                <label></label> <input type="submit" value="Delete">
            </form>
        </td>
    </tr>
</table>
<br>
<br>
<table align="center" width="100%">
    <tr>
        <td>
            <table border="1">
                <tr>
                    <td>Train_ID</td>
                    <td>Route_ID</td>

                </tr>
                <c:forEach var="train" items="${trains}">
                    <tr>
                        <td>${train.train_ID}</td>
                        <td>${train.route_ID}</td>
                        <td>
                            <form action="deleteTrain" method="post">
                                <input type="text" name="train_ID" value="${train.train_ID}" hidden>
                                <input type="submit" value="Delete">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
        <td>
            <table border="1">
                <tr>
                    <td>Carriage_ID</td>
                    <td>Train_ID</td>
                    <td>Number</td>
                    <td>Type</td>
                    <td>Total seats</td>
                    <td>Reserved seats</td>
                </tr>
                <c:forEach var="carriage" items="${carriages}">
                    <tr>
                        <td>${carriage.carriage_ID} </td>
                        <td>${carriage.train_ID}</td>
                        <td>${carriage.carriageNumber}</td>
                        <td>${carriage.type}</td>
                        <td>${carriage.totalSeats} </td>
                        <td>${carriage.reservedSeats}</td>
                        <td>
                            <form action="deleteCarriage" method="post">
                                <input type="text" name="carriage_ID" value="${carriage.carriage_ID}" hidden>
                                <input type="submit" value="Delete">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>

<jsp:include page="toAdminHome.html"></jsp:include>
</body>
</html>
