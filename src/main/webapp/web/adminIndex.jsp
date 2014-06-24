<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="<c:url value="/web/styles.css"/>" rel="Stylesheet" type="text/css"/>

</head>

<body>

<jsp:include page="adminHeader.jsp"/>

<table>

<c:forEach var="admin" items="${admins}">

<tr>


<td>${admin.firstName}</td>

<td>${admin.lastName}</td>

<td>${admin.email}</td>


</tr>

</c:forEach>



</table>

</body>

</html>