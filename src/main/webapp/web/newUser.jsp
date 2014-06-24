<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> <spring:message code="create.user.title" /> </title>

	<link href="<c:url value="web/styles.css"/>" rel="Stylesheet" type="text/css"/>

</head>
<body>

	<jsp:include page="header.jsp"/>
	
	${errorMessage}

	<h3>
		<spring:message code="create.user.header" />
	</h3>

	<form method="post">
		<table>
			<tr>
				<td>
					<spring:message code="user.form.label.firstName" />
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
					<spring:message code="user.form.label.lastName" />
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
					<spring:message code="user.form.label.co" />
				</td>
				<td>
					<input type="text" name="co"/>
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
					<input type="text" name="street"/>
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
					<input type="text" name="postal"/>
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
					<input type="text" name="city"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="city" /></font>
				</td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="user.form.label.country" />
				</td>
				<td>
					<input type="text" name="country"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="country" /></font>
				</td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="user.form.label.phone" />
				</td>
				<td>
					<input type="text" name="phone"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="phone" /></font>
				</td>
			</tr>
			<tr>
			<td>
			<input type="radio" name="gender" value="male"/> Male <br>
            <input type="radio" name="gender" value="female"/> Female <br>
			</td>
			</tr>
			<tr>
				<td>
					<spring:message code="user.form.label.email" />
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
					<spring:message code="user.form.label.password" />
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
					<input type="submit" name="_eventId_createNewUser" value="Add User"/>
				</td>
				<td/>
			</tr>
		</table>
	</form>

</body>
</html>