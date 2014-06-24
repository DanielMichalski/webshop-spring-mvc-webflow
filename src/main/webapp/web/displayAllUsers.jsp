<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title> <spring:message code="display.all.user.title" /> </title>

</head>

<body>

<h3>

<spring:message code="display.all.user.header" />

</h3>
<a href="displayAllUsersForm">display all users</a> 

<table>

<!-- <c:out value="${users}"></c:out> -->

<c:forEach var="user" items="${users}">

<tr>

<td>${user.firstName}</td>

<td>${user.lastName}</td>

<td>${user.co}</td>

<td>${user.street}</td>

<td>${user.postal}</td>

<td>${user.city}</td>

<td>${user.country}</td>

<td>${user.phone}</td>

<td>${user.gender}</td>

<td>${user.email}</td>



</tr>

</c:forEach>



</table>

</body>

</html>