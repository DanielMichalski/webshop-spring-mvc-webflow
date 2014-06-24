<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<title>Insert title here</title>

	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>	


	<script type="text/javascript">
	
		function doSearch() {
			
			alert("Hej!");
			
			$.getJSON( "searchProduct",
				
					{ CHARS: $("#searchBox").val()},
					
					function(data) {

						$("#results").text("");
						
						for(var index in data) {
							$("#results").append("<p>" + data[index].title "</p>");
						}
					}
			);
			
		}
	
	</script>

</head>

<body>

	<h1>Search Product</h1>
	
	<input type="text" onKeyUp="doSearch();" id="searchBox" />
	
	<div id="results">
	 	Results Here..
	</div>

</body>
</html>