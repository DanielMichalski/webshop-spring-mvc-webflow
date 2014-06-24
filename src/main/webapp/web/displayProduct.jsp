<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> <spring:message code="get.product.title" /> </title>
</head>
<body>
<h3>
<spring:message code="get.product.header" />
</h3>
<table>
<tr>
<td align="right"><b><spring:message code="product.form.label.referenceNumber" /></b>:</td>
<td>${product.referenceNumber}</td>
</tr>	
<tr>
<td align="right"><b><spring:message code="product.form.label.productName" /></b>:</td>
<td>${product.productName}</td>
</tr>	
<tr>
<td align="right"><b><spring:message code="product.form.label.price" /></b>:</td>
<td>${product.price}</td>
</tr>	
<tr>
<td align="right"><b><spring:message code="product.form.label.brand" /></b>:</td>
<td>${product.brand}</td>
</tr>	
<tr>
<td align="right"><b><spring:message code="product.form.label.description" /></b>:</td>
<td>${product.description}</td>
</tr>	
</table>




</body>
</html>