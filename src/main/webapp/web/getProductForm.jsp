<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> <spring:message code="get.product.title" /> </title>
</head>
<body>

	<jsp:include page="adminHeader.jsp"/>

<center>
<h3>
<spring:message code="get.product.header" />
</h3>

<form method="post">
<table>
<tr>
<td>
<spring:message code="product.form.label.referenceNumber" />
</td>
<td>
<input type="text" name="referenceNumber" value="${product.referenceNumber}"/>
</td>
<td>
<font color="red" size="2"><form:errors path="referenceNumber" /></font>
</td>
</tr>
<tr>
<td colspan="2" align="right">
<input type="submit" name="_eventId_updateProductFlow2" value="<spring:message code="submit.button" />"/>
</td>
<td/>
</tr>
</table>
</form>

</center>
</body>
</html>