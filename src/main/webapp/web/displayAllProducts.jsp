<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE>

<html>

<head>

<title> <spring:message code="display.all.product.title" /> </title>

<link href="<c:url value="/web/styles.css"/>" rel="Stylesheet" type="text/css"/>

</head>

<body>

	<jsp:include page="header.jsp"/>
	
	<div id="products">

	<ul>

    <c:forEach var="product" items="${products}">

		<li>

            <h2> ${product.productName}</>
            
            <p>

            <span> Price: ${product.price} SEK

            <p> Brand: ${product.brand}</p>

            <p> Description: ${product.description}</p>
            
            <p> Genre: ${product.genre}</p>
            
            <p> State: ${product.state} </p>
           
           
           <c:set var="ADDCART" value="/spring-friday/addToCart"/>
           <form method="post" action="${ADDCART}">
                    
          
		   <input type="hidden" name="id" value="${product.referenceNumber}"/>
	       <input type="submit" value="Add"/> 
		   </form>
            
            </span>
            
            </p>
			
        </li>

    </c:forEach>
    
	</ul>

</div>

</body>

</html>