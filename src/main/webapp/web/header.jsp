<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="todaysDate" value="<%= new java.util.Date() %>"/>

<h1>Group <span>Red</span> <small>best group</small></h1>

<div id="links">
	<ul>
		<li><a href="<%=request.getContextPath()%>/displayAllProductsForm">All Products</a></li>
		<li><a href="<%=request.getContextPath()%>/viewCart">Shopping Cart</a></li>
		<li><a href="<%=request.getContextPath()%>/createUserForm">Register</a></li>
		<li><a href="<%=request.getContextPath()%>/login">Login</a></li>
	</ul>	
</div>
<br>
<div id="links2">
	<ul>
		<li><a href="<%=request.getContextPath()%>/displayGenre/house">House</a></li>
		<li><a href="<%=request.getContextPath()%>/displayGenre/techno">Techno</a></li>
		<li><a href="<%=request.getContextPath()%>/displayGenre/trance">Trance</a></li>
		<li><a href="<%=request.getContextPath()%>/displayGenre/drumbass">Drum & Bass</a></li>
		<li><a href="<%=request.getContextPath()%>/displayGenre/electro">Electro</a></li>
	</ul>
</div>