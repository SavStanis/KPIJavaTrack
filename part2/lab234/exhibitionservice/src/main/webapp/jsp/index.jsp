<%@ page import="java.util.List" %>
<%@ page import="com.savstanis.exhibitionservice.model.entity.Exhibition" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exhibition Service</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<script type="text/javascript">
    const buyTicket = (clicked_id) => {
        const body = JSON.stringify({exhibitionId: clicked_id});
        fetch('${pageContext.request.contextPath}/tickets', {method: 'POST', body: body}).then(console.log("success"));
        alert("You have bought ticket!");
    }
    const cancelTicket = (clicked_id) => {
        const body = JSON.stringify({exhibitionId: clicked_id});
        fetch('${pageContext.request.contextPath}/exhibitions/cancel', {method: 'POST', body: body}).then(
            (response) => {
                console.log(response);
                document.location.reload();
            }
        );
    }

    const filterByTitle = (titleExample) => {
        window.location.href = '${pageContext.request.contextPath}/filter/?type=byTitle&title='+titleExample;
    }

    const filterCheaperThan = (price) => {
        window.location.href ='${pageContext.request.contextPath}/filter/?type=cheaper&price='+price;
    }

    const filterMoreExpensiveThan = (price) => {
        window.location.href ='${pageContext.request.contextPath}/filter/?type=expensive&price='+price;
    }

    const filterByDate = (date) => {
        window.location.href ='${pageContext.request.contextPath}/filter/?type=byDate&date='+date;
    }

    const toDefault = () => {
        window.location.href ='${pageContext.request.contextPath}' + '/';
    }

    const selectOnChange = (value) => {
        let newHtml;
        switch (value) {
            case "title": {
                newHtml = '<input type="text" id="filter-input"/>' +
                    '<button onclick=filterByTitle(document.getElementById("filter-input").value)>Filter</button>' +
                    '<button onclick=toDefault()>Clear</button>';
                break;
            }
            case "cheaper": {
                newHtml = '<input type="number" step="0.01" min="0" id="filter-input"/>' +
                    '<button onclick=filterCheaperThan(document.getElementById("filter-input").value)>Filter</button>' +
                    '<button onclick=toDefault()>Clear</button>';
                break;
            }
            case "expensive": {
                newHtml = '<input type="number" step="0.01" min="0" id="filter-input"/>' +
                    '<button onclick=filterMoreExpensiveThan(document.getElementById("filter-input").value)>Filter</button>' +
                    '<button onclick=toDefault()>Clear</button>';
                break;
            }
            case "date": {
                newHtml = '<input type="date" id="filter-input"/>' +
                    '<button onclick=filterByDate(document.getElementById("filter-input").value)>Filter</button>' +
                    '<button onclick=toDefault()>Clear</button>';
                break;
            }
        }
        document.getElementById('filter-input-holder').innerHTML = newHtml;
    }
</script>
<body>
<c:if test="${sessionScope.get('email') != null}">
    <h2>Hello ${sessionScope.get('email')}</h2>
    <a href="${pageContext.request.contextPath}/tickets">My tickets</a>
    <br/>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</c:if>
<c:if test="${'admin'.equals(sessionScope.get('role'))}">
    <a href="${pageContext.request.contextPath}/exhibitions/create">Create exhibition</a>
</c:if>
<c:if test="${sessionScope.get('email') == null}">
    <h2>Hello</h2>
    <a href="${pageContext.request.contextPath}/login">Login</a>
    <br/>
    <a href="${pageContext.request.contextPath}/register">Register</a>
</c:if>

<div>

    <div style="display: inline-flex" class="filters">
        Filter by
        <select onchange="selectOnChange(this.options[this.selectedIndex].value)" style="margin-left: 5px;margin-right: 5px">
            <option value="title" selected>title</option>
            <option value="cheaper">price (cheaper)</option>
            <option value="expensive">price (more expensive)</option>
            <option value="date">date</option>
        </select>
        <div id="filter-input-holder"style="margin-right: 5px">
            <input type="text" id="filter-input">
            <button onclick=filterByTitle(document.getElementById("filter-input").value)>Filter</button>
            <button onclick=toDefault()>Clear</button>
        </div>
    </div>
    <table>
        <caption>Exhibitions</caption>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Price</th>
            <th>Opening date</th>
            <th>Closing date</th>
        </tr>
        <c:forEach var="exhibition" items="${exhibitions}">
            <tr>
                <td>${exhibition.id}</td>
                <td>${exhibition.title}</td>
                <td>${exhibition.price}</td>
                <td>${exhibition.openingDate}</td>
                <td>${exhibition.closingDate}</td>
                <c:if test="${sessionScope.get('email') != null}">
                    <td>
                        <button id="${exhibition.id}" onclick="buyTicket(this.id)">Buy ticket</button>
                    </td>
                </c:if>
                <c:if test="${'admin'.equals(sessionScope.get('role'))}">
                    <td>
                        <button id="${exhibition.id}" onclick="cancelTicket(this.id)">Cancel</button>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
