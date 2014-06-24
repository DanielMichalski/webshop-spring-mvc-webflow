<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<title>Please enter your customer Id</title>
	
	<link href="<c:url value="web/styles.css"/>" rel="Stylesheet" type="text/css"/>
	
</head>

<body>

	<jsp:include page="header.jsp"/>

<h1>Please enter your customer Id</h1>

${errorMessage}

<form method="post">
	
	<strong>Customer Email: </strong>
	
	<input type="text" name="email"/>
	
	<strong>Customer Password: </strong>
	
	<input type="text" name="password"/>
	
	<input type="submit" name="_eventId_login" value="Login"/>
	<input type="submit" name="_eventId_newUser" value="Register"/>
	
</form>

</body>
</html>