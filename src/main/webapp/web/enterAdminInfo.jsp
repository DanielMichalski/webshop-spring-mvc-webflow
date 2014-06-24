<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<h1>Group <span>Red</span> <small>best group</small></h1>

<head>
	<title>Admin Login</title>
	
	<link href="<c:url value="web/styles.css"/>" rel="Stylesheet" type="text/css"/>
	
</head>

<body>

${errorMessage}

<form method="post">
	
	<strong>Customer Email: </strong>
	
	<input type="text" name="email"/>
	
	<strong>Customer Password: </strong>
	
	<input type="text" name="password"/>
	
	<input type="submit" name="_eventId_adminLogin" value="Login"/>
	
</form>

</body>
</html>