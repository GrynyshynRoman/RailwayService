<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: GrynyshynRoman
  Date: 03.08.2016
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Railway</title>
</head>
<body>
<h1 align=center> RAILWAY </h1>
<c:if test="${sessionScope.role.role eq 'admin'}">
    <jsp:include page="adminPanel.html"></jsp:include>
</c:if>
<div align=center>
    <table border="1">
        <tr>
            <td>
                Search:
                <form action="search" method="get">
                    From: <input type="text" name="from">
                    To: <input type="text" name="to"><br>
                    Date: <input type="date" name="date">
                    <input type="submit">
                </form>
            </td>
            <td>
                <jsp:include page="profileBar.jsp"/>
            </td>
        </tr>
    </table>

</div>
<div align=center>

</div>
<div align=center>

</div>
</body>
</html>
