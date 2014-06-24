<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<title>Product Added Successfully</title>
	
	<link href="<c:url value="web/styles.css"/>" rel="Stylesheet" type="text/css"/>
	
</head>

<body>

	<jsp:include page="header.jsp"/>

	<p>You have added the product "${product.productName}"</p>
	<a href="viewCart">View Cart Contents</a>
</body>
</html>