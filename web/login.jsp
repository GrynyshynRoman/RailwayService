<%--
  Created by IntelliJ IDEA.
  User: GrynyshynRoman
  Date: 17.08.2016
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
Enter:
<form action="j_security_check" method="post">
    <input placeholder="login:" type="text" size="20" name="j_username"><br>
    <input placeholder="password:" type="password" name="j_password"><br>
    <input type="submit" value="Log in">
</form>
<a href="registration.jsp">Register</a>
</body>
</html>
