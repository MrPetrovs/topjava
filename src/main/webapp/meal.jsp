<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ru">
<head>
    <title>Add new user</title>
</head>
<body>

<form method="POST" action='listMeal' name="addMeal">
    <input type="hidden" name="id"
           value="<c:out value="${requestScope.meal.mealId}" />" /> <br />
    Calories : <input type="text"  name="calories"
                     value="<c:out value="${requestScope.meal.calories}" />" /> <br />
    Description : <input
        type="text" name="description"
        value="<c:out value="${requestScope.meal.description}" />" /> <br />
    DateTime : <input
        type="text" name="date"
        value="${requestScope.meal.dateTime.toString().replace('T', ' ')}"/> <br />
        <input type="submit" value="Submit" />
</form>
</body>
</html>