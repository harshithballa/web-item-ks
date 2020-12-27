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
			
			<input type="button" value="HOME" 
				   onclick="window.location.href='items?command=LIST'; return false;"
				   class="add-student-button"
			/>
		
			<table>
			
				<tr>
					<th>Customer Name</th>
					<th>Product Name</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Bill Amount</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempItem" items="${ORDER_LIST}">
				

					
					<!--  set up a link to delete a student -->
					<c:url var="deleteLink" value="items">
						<c:param name="command" value="DELETE" />
						<c:param name="itemId" value="${tempItem.orderId}" />
					</c:url>
					
					<c:url var="updateImageLink" value="items">
						<c:param name="command" value="LoadImage" />
						<c:param name="itemId" value="${tempItem.orderId}" />
					</c:url>
																		
					<tr>
						<td> ${tempItem.customerName} </td>
						<td> ${tempItem.productName} </td>
						<td> ${tempItem.quantity} </td>
						<td> ${tempItem.price} </td>
						<td> ${tempItem.billAmount} </td>
						
						<td> 
							<a href="${tempLink}">View Order</a> 
							 | 
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to cancel this order?'))) return false">
							Cancel Order</a>
						</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>


</html>








