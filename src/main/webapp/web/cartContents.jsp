<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>

<link href="<c:url value="web/styles.css"/>" rel="Stylesheet" type="text/css"/>

</head>
<body>

<jsp:include page="header.jsp"/>

<h1>Here are the contents of your cart</h1>
		<ul>
		<c:forEach items="${lines}" var="nextLine">
		<li>
			<h2>${nextLine.product.productName}</h2>

			<h2>${nextLine.quantity}</h2>

			<h2>${nextLine.totalPrice}</h2>
			
			
		</li>
		<br>
		</c:forEach>
		</ul>
		
		
	<a href="checkout-flow">Click here to checkout</a>

</body>
</html>