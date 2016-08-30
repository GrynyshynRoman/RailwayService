<%--
  Created by IntelliJ IDEA.
  User: GrynyshynRoman
  Date: 09.08.2016
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="ua.nure.hrynyshyn.resources.pageContent"/>

<html>
<head>
    <title>Stations</title>
    <link rel="stylesheet" href="test.css">
</head>
<body>
<%@include file="languagePanel.html" %>
<%@include file="adminPanel.jsp" %>
<br>
<br>
<table border="1" align="center" width="50%">
    <tr>
        <td>
            <form action="addStation" method="post">
                <div>
                    <label><fmt:message key="stations.name"/> :</label><input type="text" name="name">
                    <label><fmt:message key="stations.city"/>:</label> <input type="text" name="city">
                    <label><fmt:message key="stations.state"/>:</label> <input type="text" name="state">
                    <label><fmt:message key="stations.country"/>:</label><input type="text" name="country">
                    <br>
                    <label></label><input type="submit" value="<fmt:message key="admin.add"/>">
                </div>
            </form>
        </td>
        <td>
            <form action="editStation" method="post">
                <div>
                    <label><fmt:message key="stations.id"/> :</label> <input type="text" name="id">
                    <label><fmt:message key="stations.name"/> :</label><input type="text" name="name">
                    <label><fmt:message key="stations.city"/>:</label> <input type="text" name="city">
                    <label><fmt:message key="stations.state"/>:</label> <input type="text" name="state">
                    <label><fmt:message key="stations.country"/>:</label><input type="text" name="country">
                    <br>
                    <label></label><input type="submit" value="<fmt:message key="admin.edit"/>">
                </div>
            </form>
        </td>
        <td>
            <form action="deleteStation" method="post">
                <div>
                    <label><fmt:message key="stations.id"/>:</label> <input type="text" name="id">
                    <br>
                    <label></label><input type="submit" value="<fmt:message key="admin.delete"/>">
                </div>
            </form>
            <br>
            <div>
                <c:if test="${isDeleted ne true && isDeleted ne null}">
                    <strong><fmt:message key="stations.error"/></strong>
                </c:if>
            </div>

        </td>
    </tr>
</table>
<br>
<br>
<h3 align="center"><fmt:message key="stations.stations"/></h3>
<br>
<table border="1" align="center" width="50%">
    <tr>
        <td><fmt:message key="stations.id"/></td>
        <td><fmt:message key="stations.name"/></td>
        <td><fmt:message key="stations.city"/></td>
        <td><fmt:message key="stations.state"/></td>
        <td><fmt:message key="stations.country"/></td>
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
</body>
</html>