<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>

<head>
	<title>Online Store App</title>	
 	<link type="text/css" rel="stylesheet" href="css/style.css"> 
</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>Kirana Store</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		
			<!-- <h4><a href="index.html">Home Page</a></h4> -->
		
			<!-- put new button: Add Student -->
			
			<!-- <input type="button" value="Add Item" 
				   onclick="window.location.href='add-item-form.jsp'; return false;"
				   class="add-student-button"
			/> -->
		
			<table>
			
				<tr>
					<th>Image</th>
					<th>Name</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Action</th>
					
				</tr>
				
				<c:forEach var="tempItem" items="${ITEM_LIST}">
				
					<!-- set up a link for each student -->
					<%-- <c:url var="tempLink" value="items">
						<c:param name="command" value="LOAD" />
						<c:param name="itemId" value="${tempItem.id}" />
					</c:url> --%>
					
				
					<c:url var="buyLink" value="items">
						<c:param name="command" value="BUY" />
						<c:param name="itemId" value="${tempItem.id}" />
					</c:url>
																		
					<tr>
						<td> <img src="data:image/jpg;base64,${tempItem.base64Image}" width="100" height="75"/> </td>
						<td> ${tempItem.name} </td>
						<td> ${tempItem.quantity} </td>
						<td> ${tempItem.price} </td>
						<td> <a href ="${buyLink}">Buy Now</a> </td>
						
						<%-- <td> 
							<a href="${tempLink}">Update</a> 
							 | 
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this Item?'))) return false">
							Delete</a>
						</td> --%>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>


</html>








