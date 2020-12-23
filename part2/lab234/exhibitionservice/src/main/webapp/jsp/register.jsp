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
<h2>Register</h2>
<form method="post">
    <c:if test="${requestScope.get('error') != null}">
        <div style="color: #ff0000">${requestScope.get('error')}</div>
    </c:if>
    <label>Name: </label>
    <input type="text" name="name" class="input" required>
    <br/>
    <br/>
    <label>Email: </label>
    <input type="email" name="email" id="email" class="input" required>
    <br/>
    <br/>
    <label>Password: </label>
    <input type="password" name="password" id="password" placeholder class="input" required>
    <br/>
    <br/>
    <button type="submit" class="button">Register</button>
    <br/>
    <a href="${pageContext.request.contextPath}/login" class="redirect">Login</a>
</form>
</body>
</html>
