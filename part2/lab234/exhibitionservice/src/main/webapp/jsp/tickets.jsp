<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exhibition service</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>

<body>
<div>
    <table>
        <div>
            <a href="${pageContext.request.contextPath}/">Return</a>
            <c:if test="${sessionScope.get('email') != null}">
                <a href="${pageContext.request.contextPath}/logout">Logout</a>
            </c:if>
        </div>
        <caption>My tickets</caption>
        <tr>
            <th>Id</th>
            <th>Exhibition title</th>
            <th>Price</th>
            <th>Purchase time</th>
        </tr>
        <c:forEach var="ticket" items="${tickets}">
            <tr >
                <td>${ticket.id}</td>
                <td>${ticket.exhibitionTitle}</td>
                <td>${ticket.price}</td>
                <td>${ticket.purchaseTime}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
