<%--
  Created by IntelliJ IDEA.
  User: GrynyshynRoman
  Date: 12.08.2016
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Administrator</title>
</head>
<body>
<table align=center>
    <tr>
        <td>
            <form action="stationsEdit" method="get">
                <input type="submit" value="Stations">
            </form>
        </td>
        <td>
            <form action="routesEdit" method="get">
                <input type="submit" value="Routes and way stations">
            </form>
        </td>
        <td>
            <form action="trainsEdit" method="get">
                <input type="submit" value="Trains and carriages">
            </form>
        </td>
    </tr>
</table>
</body>
</html>
