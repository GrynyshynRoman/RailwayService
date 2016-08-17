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
<table border="1">
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
</table>
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
                <form action="deleteStation" method="post">
                    <input type="text" name="id" value="${train.train_ID}" hidden>
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="toAdminHome.html"></jsp:include>
</body>
</html>
