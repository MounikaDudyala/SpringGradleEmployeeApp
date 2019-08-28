<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<%@ page isELIgnored="false"%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form:form action="${request}" method="POST" modelAttribute="employee">
		<table style="width: 50%">
			<tr>
				<td><form:hidden path="empId" value="${employee.empId}" /></td>
			</tr>
			<tr>
				<td>First Name*</td>
				<td><form:input path="firstName" required="true"
						pattern="[a-zA-Z]{1,15}" value="${employee.firstName}" /></td>
			</tr>
			<tr>
				<td>Last Name*</td>
				<td><form:input path="lastName" required="true"
						pattern="[a-zA-Z]{1,15}" value="${employee.lastName}" /></td>
			</tr>
			<tr>
				<td>ManagerId*</td>
				<td><form:input path="managerId" required="true" pattern="[1-9]{1,5}"
					value="${employee.managerId}" />
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="${request}" /></td>
			</tr>
		</table>
	</form:form>
	<a href="list">Cancel</a>
</body>
</html>