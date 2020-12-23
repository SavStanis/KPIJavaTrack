
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<h1>Create exhibition</h1>
<div>
    <a href="${pageContext.request.contextPath}/">Return</a>
    <c:if test="${sessionScope.get('email') != null}">
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </c:if>
</div>
<form method="post">
    <c:if test="${requestScope.get('error') != null}">
        <div style="color: #ff0000">${requestScope.get('error')}</div>
    </c:if>
    <label>Title: </label>
    <input type="text" name="title" id="title" class="input" required>
    <br/>
    <br/>
    <label>Price: </label>
    <input type="number" step="0.01" min="0" name="price" id="price" class="input" required>
    <br/>
    <br/>
    <label>Opening date: </label>
    <input type="date" name="openingDate" id="openingDate" placeholder class="input" required>
    <br/>
    <br/>
    <label>Closing date: </label>
    <input type="date" name="closingDate" id="closingDate" placeholder class="input" required>
    <br/>
    <br/>
    <button type="submit" class="button">Create</button>
    <br/>
</form>
</body>
</html>
