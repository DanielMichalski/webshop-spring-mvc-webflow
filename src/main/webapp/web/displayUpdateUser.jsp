<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> <spring:message code="update.user.title" /> </title>
</head>
<body>
	<h3>
		<spring:message code="update.user.header" />
	</h3>

	<form:form modelAttribute="userForm" method="post">
		<table>
			<tr>
				<td>
					<spring:message code="user.form.label.firstName" />
				</td>
				<td>
					<form:input path="firstName" value="${user.firstName}"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="firstName" /></font>
				</td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="user.form.label.lastName" />
				</td>
				<td>
					<form:input path="lastName" value="${user.lastName}"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="lastName" /></font>
				</td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="user.form.label.co" />
				</td>
				<td>
					<form:input path="co" value="${user.co}"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="co" /></font>
				</td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="user.form.label.street" />
				</td>
				<td>
					<form:input path="street" value="${user.street}"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="street" /></font>
				</td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="user.form.label.postal" />
				</td>
				<td>
					<form:input path="postal" value="${user.postal}"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="postal" /></font>
				</td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="user.form.label.city" />
				</td>
				<td>
					<form:input path="city" value="${user.city}"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="city" /></font>
				</td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="user.form.label.phone" />
				</td>
				<td>
					<form:input path="phone" value="${user.phone}"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="phone" /></font>
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