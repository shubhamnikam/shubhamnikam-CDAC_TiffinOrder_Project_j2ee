<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h3>In Customer :: Menu</h3>

	<form action="post">
		<table style="margin: auto" border="1">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Description</th>
				<th>Price</th>
				<th>Quantity</th>
			</tr>
			<c:forEach var="dailyMenu"
				items="${requestScope.dailyMenuList_requestScope}">
				<tr>
					<td>${dailyMenu.menuId}</td>
					<td>${dailyMenu.menuName}</td>
					<td>${dailyMenu.menuDescription}</td>
					<td>${dailyMenu.menuPrice}</td>
					<td>0</td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="checkout" />
	</form>


</body>
</html>