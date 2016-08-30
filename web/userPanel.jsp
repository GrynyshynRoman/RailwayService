<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="ua.nure.hrynyshyn.resources.pageContent"/>
<table align=center width="40%" border="1">
    <tr>
        <td align="center">
            <a href="index.jsp"><fmt:message key="navigation.toMain"/> </a>
        </td>
    </tr>
</table>
</body>
</html>
