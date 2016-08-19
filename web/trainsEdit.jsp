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
</head>
<body>
<table>
    <tr>
        <td>
            Add train:<br>
            <form action="addTrain" method="post">
                Route id: <input type="text" name="route_ID"><br>
                <input type="submit" value="Add">
            </form>
        </td>
        <td>
            Edit train:<br>
            <form action="editTrain" method="post">
                ID: <input type="text" name="train_ID"><br>
                Route id: <input type="text" name="route_ID"><br>
                <input type="submit" value="Edit">
            </form>
        </td>
        <td>
            Delete train:<br>
            <form action="deleteTrain" method="post">
                ID: <input type="text" name="train_ID"><br>
                <input type="submit" value="Delete">
            </form>
        </td>
    </tr>
    <tr>
        <td>
            Add carriage:<br>
            <form action="addCarriage" method="post">
                Train id: <input type="text" name="train_ID"><br>
                Carriage number: <input type="text" name="carriageNumber"><br>
                Type:
                <select name="type"><br>
                    <option value="common">Common</option>
                    <option value="reservedSeat">Reserved seat</option>
                    <option value="coupe">Coupe</option>
                </select><br>
                Total seats: <input type="text" name="totalSeats"><br>
                Reserved seats: <input type="text" name="reservedSeats"><br>
                <input type="submit" value="Add">
            </form>
        </td>
        <td>
            Edit carriage:<br>
            <form action="editCarriage" method="post">
                Carriage id: <input type="text" name="carriage_ID"><br>
                Train id: <input type="text" name="train_ID"><br>
                Carriage number: <input type="text" name="carriageNumber"><br>
                Type:
                <select name="type">
                    <option value="common">Common</option>
                    <option value="reservedSeat">Reserved seat</option>
                    <option value="coupe">Coupe</option>
                </select><br>
                Total seats: <input type="text" name="totalSeats"><br>
                Reserved seats: <input type="text" name="reservedSeats"><br>
                <input type="submit" value="Edit">
            </form>
        </td>
        <td>
            Delete carriage:<br>
            <form action="deleteCarriage" method="post">
                Carriage id: <input type="text" name="carriage_ID"><br>
                <input type="submit" value="Delete">
            </form>
        </td>
    </tr>
</table>
<table>
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
