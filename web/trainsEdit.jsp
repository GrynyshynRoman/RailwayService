<%--
  Created by IntelliJ IDEA.
  User: HrynyshynRoman
  Date: 18.08.2016
  Time: 00:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="errorPage.jsp" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="ua.nure.hrynyshyn.resources.pageContent"/>
<html>
<head>
    <title>Trains edit</title>
    <link rel="stylesheet" href="test.css">
</head>
<body>
<%@include file="languagePanel.html"%>
<jsp:include page="profileBar.jsp"/>
<jsp:include page="navigationPanel.jsp"/>
<table align="center" width="90%" border="1">
    <tr>
        <td>
            <fmt:message key="trains.addTrain"/>:<br>
            <form action="addTrain" method="post">
                <div>
                    <label><fmt:message key="trains.routeID"/> </label><input type="text" name="route_ID">
                    <label></label><input type="submit" value="<fmt:message key="admin.add"/> ">
                </div>
            </form>
        </td>
        <td>
            <fmt:message key="trains.editTrain"/>:<br>
            <form action="editTrain" method="post">
                <label><fmt:message key="trains.id"/> :</label> <input type="text" name="train_ID"><br>
                <label><fmt:message key="trains.routeID"/> :</label> <input type="text" name="route_ID"><br>
                <label></label><input type="submit" value="<fmt:message key="admin.edit"/> ">
            </form>
        </td>
        <td>
            <fmt:message key="trains.deleteTrain"/> <br>
            <form action="deleteTrain" method="post">
                <label>ID:</label> <input type="text" name="train_ID">
                <label></label><input type="submit" value="<fmt:message key="admin.delete"/> ">
            </form>
        </td>
    </tr>
</table>
<br>
<table align="center" width="90%" border="1">
    <tr>
        <td>
            <fmt:message key="carriage.addCarriage"/> :<br>
            <form action="addCarriage" method="post">
                <label><fmt:message key="trains.id"/> </label> <input type="text" name="train_ID"><br>
                <label><fmt:message key="carriage.carriageNum"/> :</label> <input type="text" name="carriageNumber"><br>
                <label><fmt:message key="carriage.type"/> :</label>
                <select name="type">
                    <option value="common"><fmt:message key="carriage.common"/></option>
                    <option value="reservedSeat"><fmt:message key="carriage.reservSeat"/></option>
                    <option value="coupe"><fmt:message key="carriage.coupe"/></option>
                </select>
                <br>
                <label><fmt:message key="carriage.totalSeats"/> :</label> <input type="text" name="totalSeats"><br>
                <label><fmt:message key="carriage.reservSeats"/> :</label> <input type="text" name="reservedSeats"><br>
                <label></label> <input type="submit" value="<fmt:message key="admin.add"/> ">
            </form>
        </td>
        <td>
            <fmt:message key="carriage.editCarriage"/> :<br>
            <form action="editCarriage" method="post">
                <label> <fmt:message key="carriage.carriageID"/> :</label> <input type="text" name="carriage_ID"><br>
                <label><fmt:message key="trains.id"/> </label> <input type="text" name="train_ID"><br>
                <label><fmt:message key="carriage.carriageNum"/> :</label> <input type="text" name="carriageNumber"><br>
                <label><fmt:message key="carriage.type"/> :</label>
                <select name="type">
                    <option value="common"><fmt:message key="carriage.common"/></option>
                    <option value="reservedSeat"><fmt:message key="carriage.reservSeat"/></option>
                    <option value="coupe"><fmt:message key="carriage.coupe"/></option>
                </select><br>
                <label><fmt:message key="carriage.totalSeats"/> :</label> <input type="text" name="totalSeats"><br>
                <label><fmt:message key="carriage.reservSeats"/> :</label> <input type="text" name="reservedSeats"><br>
                <label></label> <input type="submit" value="<fmt:message key="admin.edit"/> ">
            </form>
        </td>
        <td>
            <fmt:message key="carriage.delCarriage"/> :<br>
            <form action="deleteCarriage" method="post">
                <label> <fmt:message key="carriage.carriageID"/> :</label> <input type="text" name="carriage_ID">
                <label></label> <input type="submit" value="<fmt:message key="admin.delete"/> ">
            </form>
        </td>
    </tr>
</table>
<br>
<br>
<table align="center" width="90%">
    <tr>
        <td>
            <table border="1">
                <tr>
                    <td><fmt:message key="trains.id"/></td>
                    <td><fmt:message key="trains.routeID"/></td>

                </tr>
                <c:forEach var="train" items="${sessionScope.trains}">
                    <tr>
                        <td>${train.train_ID}</td>
                        <td>${train.route_ID}</td>
                    </tr>
                </c:forEach>
            </table>
        </td>
        <td>
            <table border="1">
                <tr>
                    <td><fmt:message key="carriage.carriageID"/></td>
                    <td><fmt:message key="trains.id"/></td>
                    <td><fmt:message key="carriage.number"/></td>
                    <td><fmt:message key="carriage.type"/></td>
                    <td><fmt:message key="carriage.totalSeats"/></td>
                    <td><fmt:message key="carriage.reservSeats"/></td>
                </tr>
                <c:forEach var="carriage" items="${sessionScope.carriages}">
                    <tr>
                        <td>${carriage.carriage_ID} </td>
                        <td>${carriage.train_ID}</td>
                        <td>${carriage.carriageNumber}</td>
                        <td>${carriage.type}</td>
                        <td>${carriage.totalSeats} </td>
                        <td>${carriage.reservedSeats}</td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>

</body>
</html>
