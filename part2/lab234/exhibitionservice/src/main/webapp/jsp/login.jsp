<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exhibition Service</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<div>
    <a href="${pageContext.request.contextPath}/">Return</a>
</div>
<h2>Login</h2>
<br/>
<form method="post" action="${pageContext.request.contextPath}/login" class="login_form">
    <c:if test="${requestScope.get('error') != null}">
        <div style="color: #ff0000">Error: ${requestScope.get('error')}</div>
    </c:if>

    <c:if test="${requestScope.get('message') != null}">
        <div style="color: green">${requestScope.get('message')}</div>
    </c:if>

    <div>Email</div>
    <input type="email" name="email" id="email" class="input" required>
    <div>Password</div>
    <input type="password" name="password" id="password" class="input" required>
    <button type="submit" class="button">Login</button>
    <br/>
    <br/>
    <a href="${pageContext.request.contextPath}/register" class="redirect">Register</a>
</form>
</body>
</html>
