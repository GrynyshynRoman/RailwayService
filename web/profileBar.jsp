<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${user.logged}">
        <div align="center">
                ${sessionScope.user.firstName} ${sessionScope.user.lastName}
            <form action="logout" method="post">
                <input type="submit" value="Logout">
            </form>
        </div>
    </c:when>
    <c:otherwise>
        Enter:
        <form action="login" method="post">
            <table>
                <tr>
                    <td align="left">Login:</td>
                    <td align="right"><input type="text" name="login"></td>
                </tr>
                <tr>
                    <td align="left">Password:</td>
                    <td align="right"><input type="password" name="password"></td>
                </tr>
            </table>
            <div align="center">
                <input type="submit" value="Log in">
            </div>
        </form>
        <div align="center">
            <a href="registration.jsp">Register</a>
        </div>
    </c:otherwise>
</c:choose>