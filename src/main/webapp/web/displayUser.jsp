<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> <spring:message code="get.user.title" /> </title>
</head>
<body>
	<h3>
		<spring:message code="get.user.header" />
	</h3>
	<table>
		<tr>
			<td align="right"><b><spring:message code="user.form.label.firstName" /></b>:</td>
			<td>${user.firstName}</td>
		</tr>
		
		<tr>
			<td align="right"><b><spring:message code="user.form.label.lastName" /></b>:</td>
			<td>${user.lastName}</td>
		</tr>
		
		<tr>
			<td align="right"><b><spring:message code="user.form.label.co" /></b>:</td>
			<td>${user.co}</td>
		</tr>
		
		<tr>
			<td align="right"><b><spring:message code="user.form.label.street" /></b>:</td>
			<td>${user.street}</td>
		</tr>
		
		<tr>
			<td align="right"><b><spring:message code="user.form.label.postal" /></b>:</td>
			<td>${user.postal}</td>
		</tr>
		
		<tr>
			<td align="right"><b><spring:message code="user.form.label.city" /></b>:</td>
			<td>${user.city}</td>
		</tr>
		
		<tr>
			<td align="right"><b><spring:message code="user.form.label.country" /></b>:</td>
			<td>${user.country}</td>
		</tr>
		
		<tr>
			<td align="right"><b><spring:message code="user.form.label.phone" /></b>:</td>
			<td>${user.phone}</td>
		</tr>
		
		<tr>
			<td align="right"><b><spring:message code="user.form.label.gender" /></b>:</td>
			<td>${user.gender}</td>
		</tr>
		
		<tr>
			<td align="right"><b><spring:message code="user.form.label.email" /></b>:</td>
			<td>${user.email}</td>
		</tr>
	</table>
</body>
</html>