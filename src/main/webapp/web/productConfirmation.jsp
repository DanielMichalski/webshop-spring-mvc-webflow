<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> <spring:message code="create.product.confirmation.title" /> </title>

<link href="<c:url value="/web/styles.css"/>" rel="Stylesheet" type="text/css"/>

</head>
<body>

	<jsp:include page="adminHeader.jsp"/>

	<h3>
		<spring:message code="create.product.confirmation.header" />
	</h3>

</body>
</html>