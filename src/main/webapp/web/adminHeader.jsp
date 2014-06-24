<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="todaysDate" value="<%= new java.util.Date() %>"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<h1>Group <span>Red</span> <small>Admin Page</small></h1>

<link href="<c:url value="web/styles.css"/>" rel="Stylesheet" type="text/css"/>

<form method="post">
<div id="links">
	<ul>
		<li><input type="submit" name="_eventId_index" value="index" class="text_button"/></li>
		<li><input type="submit" name="_eventId_createProductFlow1" value="createProduct" class="text_button"/></li>
		<li><input type="submit" name="_eventId_updateProductFlow1" value="updateProduct" class="text_button"/></li>
		<li><input type="submit" name="_eventId_updateOrderFlow1" value="updateOrder" class="text_button"/></li>
		<li><input type="submit" name="_eventId_createAdminFlow1" value="createAdmin" class="text_button"/></li>
		<li><input type="submit" name="_eventId_logout" value="logout" class="text_button"/></li>
	</ul>
</div>
</form>
</html>