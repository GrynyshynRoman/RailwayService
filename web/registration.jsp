<%--
  Created by IntelliJ IDEA.
  User: GrynyshynRoman
  Date: 18.08.2016
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1 align="center">Registration</h1>
<form action="registration" method="post">
    <table align="center" width="50%">
        <tr>
            <td align="right">
                Login:
            </td>
            <td align="left">
                <input type="text" name="login">
            </td>
        </tr>
        <tr>
            <td align="right">
                First name:
            </td>
            <td align="left">
                <input type="text" name="firstName">
            </td>
        </tr>
        <tr>
            <td align="right">
                Last name:
            </td>
            <td align="left">
                <input type="text" name="lastName">
            </td>
        </tr>
        <tr>
            <td align="right">
                Password:
            </td>
            <td align="left">
                <input type="password" name="password">
            </td>
        </tr>
        <tr>
            <td align="right">
                Confirm password
            </td>
            <td align="left">
                <input type="password" name="confirmPassword">
            </td>
        </tr>
    </table>
    <div align="center"><input type="submit" value="Register"></div>

</form>
</body>
</html>
