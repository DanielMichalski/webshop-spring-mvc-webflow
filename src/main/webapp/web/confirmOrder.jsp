<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<link href="<c:url value="web/styles.css"/>" rel="Stylesheet" type="text/css"/>

	<title>Order Confirmation</title>	
</head>
<body>

<jsp:include page="header.jsp"/>

<h1>Order Confirmation</h1>

<ul>
<c:forEach items="${order.productsOrdered}" var="lines">
	<li>	
	    ${lines.product.productName}  		
	</li>
</c:forEach>
</ul> 

<form method="post">
	<input type="submit" name="_eventId_confirm" value="Click to confirm the order"/>
</form>
</body>
</html>