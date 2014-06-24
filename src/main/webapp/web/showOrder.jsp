<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>

<head>
	<title>Please enter your customer Id</title>
	
	<link href="<c:url value="web/styles.css"/>" rel="Stylesheet" type="text/css"/>
	
</head>

<body>

<jsp:include page="header.jsp"/>

<h1>Welcome back, ${order.user.firstName}</h1>

<ul>
<c:forEach items="${order.productsOrdered}" var="line">
<li>

${line.product.productName} 
${line.quantity}
${line.totalPrice}  

<form method="post">

<input type="number" name="quantity" id="quantity" value="${line.quantity}" max="100" min="1">

<input type="hidden" name="referenceNumber" value="${line.product.referenceNumber}"/>
<input type="hidden" name="prod" value="${line.product}"/>
<input type="submit" value="Remove Item" name="_eventId_removeProduct"/>
<input type="submit" value="Change Quantity" name="_eventId_changeQuantityItem"/>


</form>



</c:forEach>
</ul> 


<form method="post">
<input type="submit" value="Press here to continue" name="_eventId_nextStep"/>
</form>
</body>
</html>