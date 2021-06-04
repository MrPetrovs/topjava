<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <style type="text/css">
        tr, th, td {
            padding: 3px;
            border: 1px solid black;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table>
    <tr>
        <th><h3>Date</h3></th>
        <th><h3>Description</h3></th>
        <th><h3>Calories</h3></th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${requestScope.mealsList}" var="meals">
        <tr>
            <% String textColor = "green"; %>
            <c:choose>
                <c:when test="${meals.excess}">
                    <% textColor = "red"; %>
                </c:when>
            </c:choose>
            <h4>
                <td style="color:<%= textColor %>"><c:out value="${meals.dateTime.toString().replace('T', ' ')}"/></td>
            </h4>
            <h4>
                <td style="color:<%= textColor %>"><c:out value="${meals.description}"/></td>
            </h4>
            <h4>
                <td style="color:<%= textColor %>"><c:out value="${meals.calories}"/></td>
            </h4>
            <h4>
                <td>Update</td>
            </h4>
            <h4>
                <td>Delete</td>
            </h4>
        </tr>
    </c:forEach>
</table>

</body>
</html>