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
<title> <spring:message code="create.admin.title" /> </title>

<link href="<c:url value="/web/styles.css"/>" rel="Stylesheet" type="text/css"/>

</head>
<body>

	<jsp:include page="adminHeader.jsp"/>

	<h3>
		<spring:message code="create.admin.header" />
	</h3>

	<form method="post">
		<table>
			<tr>
				<td>
					<spring:message code="admin.form.label.firstName" />
				</td>
				<td>
					<input type="text" name="firstName"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="firstName" /></font>
				</td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="admin.form.label.lastName" />
				</td>
				<td>
					<input type="text" name="lastName"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="lastName" /></font>
				</td>
			</tr>

			<tr>
				<td>
					<spring:message code="admin.form.label.email" />
				</td>
				<td>
					<input type="text" name="email"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="email" /></font>
				</td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="admin.form.label.password" />
				</td>
				<td>
					<input type="text" name="password"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="password" /></font>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" name="_eventId_createAdminFlow2" value="<spring:message code="submit.button" />" />
				</td>
				<td/>
			</tr>
		</table>
	</form>

</body>
</html>