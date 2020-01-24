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
	<form method="post">
		<table style="background-color: white; margin: auto;">
			<tr>
				<td>Enter Name</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>Enter User Email</td>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td>Enter Password</td>
				<td><input type="text" name="password" /></td>
			</tr>
			<tr>
				<td>Enter Phone</td>
				<td><input type="text" name="phone" /></td>
			</tr>

			<tr>
				<td><input type="submit" value="SignUp" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
