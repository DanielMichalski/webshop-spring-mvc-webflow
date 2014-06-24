<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> <spring:message code="get.order.title" /> </title>
</head>
<body>

	<jsp:include page="adminHeader.jsp"/>

<center>
<h3>
<spring:message code="get.order.header" />
</h3>

<form method="post">
<table>

<tr>
<td>
<spring:message code="order.form.label.state" />
</td>

<td>
<select name="state">
<option value="null"></option>
<option value="Ordered">Ordered</option>
<option value="Packed">Packed</option>
<option value="Shipped">Shipped</option>
<option value="Canceled">Canceled</option>
<option value="Deleted">Deleted</option>
</select>
</td>
</tr>

<tr>
<td>
<spring:message code="user.form.label.email" />
</td>
<td>
<input type="text" name="email"/>
</td>
</tr>

<tr>
<td colspan="2" align="right">
<input type="submit" name="_eventId_updateOrderFlow2" value="<spring:message code="submit.button" />"/>
</td>
<td/>
</tr>
</table>
</form>

</center>
</body>
</html>