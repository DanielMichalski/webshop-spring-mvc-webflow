<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> <spring:message code="create.product.title" /> </title>

<link href="<c:url value="/web/styles.css"/>" rel="Stylesheet" type="text/css"/>

</head>
<body>

	<jsp:include page="adminHeader.jsp"/>

	<h3>
		<spring:message code="create.product.header" />
	</h3>

	<form method="post">
		<table>
			<tr>
				<td>
					<spring:message code="product.form.label.productName" />
				</td>
				<td>
					<input type="text" name="productName"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="productName" /></font>
				</td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="product.form.label.referenceNumber" />
				</td>
				<td>
					<input type="text" name="referenceNumber"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="referenceNumber" /></font>
				</td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="product.form.label.price" />
				</td>
				<td>
					<input type="text" name="price"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="price" /></font>
				</td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="product.form.label.brand" />
				</td>
				<td>
					<input type="text" name="brand"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="brand" /></font>
				</td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="product.form.label.description" />
				</td>
				<td>
					<input type="text" name="description"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="description" /></font>
				</td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="product.form.label.genre" />
				</td>
				<td>
					<input type="text" name="genre"/>
				</td>
				<td>
					<font color="red" size="2"><form:errors path="genre" /></font>
				</td>
			</tr>
			
			<tr>
			<td>	
			<select name="state">
			<option value="In Stock">In Stock</option>
			<option value="Out Of Stock">Out Of Stock</option>
			<option value="Deleted">Deleted</option>
			</select>
			</td>
			<td>
				<font color="red" size="2"><form:errors path="state" /></font>
			</td>
			</tr>
			
			
			<tr>
				<td colspan="2" align="right">
					<input type="submit" name="_eventId_createProductFlow2" value="<spring:message code="submit.button" />" />
				</td>
				<td/>
			</tr>
		</table>
	</form>

</body>
</html>