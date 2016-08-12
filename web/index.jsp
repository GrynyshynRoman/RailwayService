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
<div align=center>
    <jsp:include page="loginForm.html"/>
</div>
<div align=center>
    <form action="search" method="get">
        From: <input type="text" name="from">
        To: <input type="text" name="to"><br>
        Date: <input type="date" name="date">
        <input type="submit">
    </form>
</div>
<div align=center>
    <form action="addStation" method="post">
        Name: <input type="text" name="name"><br>
        City: <input type="text" name="city"><br>
        State: <input type="text" name="state"><br>
        Country:<input type="text" name="country"><br>
        <input type="submit">
    </form>
</div>
</body>
</html>
