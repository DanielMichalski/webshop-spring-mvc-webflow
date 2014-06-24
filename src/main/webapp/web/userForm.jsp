<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> <spring:message code="login.title" /> </title>

<link href="<c:url value="web/styles.css"/>" rel="Stylesheet" type="text/css"/>

</head>
<body>

	<jsp:include page="header.jsp"/>

	<h3>
		<spring:message code="login.header" />
	</h3>

	<form:form modelAttribute="user" method="post">
		<table>
			<tr>
				<td>
					<spring:message code="user.form.label.email" />
				</td>
				<td>
					<form:input path="email"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="email" /></font>
				</td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="user.form.label.password" />
				</td>
				<td>
					<form:input path="password"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="password" /></font>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="<spring:message code="submit.button" />" />
				</td>
				<td/>
			</tr>
		</table>
	</form:form>

</body>
</html>