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
<title> <spring:message code="update.order.title" /> </title>

<link href="<c:url value="/web/styles.css"/>" rel="Stylesheet" type="text/css"/>

</head>
<body>

	<jsp:include page="adminHeader.jsp"/>

	<h3>
		<spring:message code="update.order.header" />
	</h3>

	<form method="post">
		<table>
			<tr>
				<td>
					<spring:message code="user.form.label.email" />
				</td>
				<td>
					<input type="text" name="email" value="${order.user.email}" readonly="readonly"/>
				</td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="user.form.label.firstName" />
				</td>
				<td>
					<input type="text" name="firstName" value="${order.user.firstName}"/>
				</td>
			</tr>
			
			<tr>
				<td>
					<spring:message code="user.form.label.lastName" />
				</td>
				<td>
					<input type="text" name="lastName" value="${order.user.lastName}"/>
				</td>
			</tr>
			
			
			
			<tr>
				<td>
					<spring:message code="user.form.label.lastName" />
				</td>
				<td>
					<input type="text" name="lastName" value="${order.user.lastName}"/>
				</td>
			</tr>
			
			<tr>
			<td>	
			Current order state: ${order.state}
			<select name="state">
			<option value="null"></option>
			<option value="Ordered">Ordered</option>
			<option value="Packed">Packed</option>
			<option value="Shipped">Shipped</option>
			<option value="Canceled">Canceled</option>
			<option value="Deleted">Deleted</option>
			</select>
			</td>
			</tr>
			
			
			<tr>
				<td colspan="2" align="right">					
					<input type="submit" name="_eventId_updateOrderFlow4" value="Update"/>
				</td>
				<td/>
			</tr>
			
			
			
			<tr>
			<td>			
			<div id="products">

			<ul>

   			 <c:forEach var="line" items="${order.productsOrdered}">

			<li>

            <h2> ${line.product.productName} </h2>
            
            <p>

            <span> Price: ${line.product.price} SEK

            <p> Brand: ${line.product.brand}</p>

            <p> Description: ${line.product.description}</p>
            
            
            </span>
            
            </p>
			
        </li>

    </c:forEach>
    
	</ul>

</div>
</td>
</tr>
			
			
		</table>
	</form>
	
	
	
	

</body>
</html>