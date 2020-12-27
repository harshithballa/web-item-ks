<!DOCTYPE html>
<html>

<head>
	<title>Buy Item</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Kirana Store</h2>
		</div>
	</div>
	
	<div id="container">
		<!-- <h4><a href="index.html">Home Page</a></h4> -->
		<h3>Order Summary</h3>
		
		<form action="items" method="POST" enctype="multipart/form-data">
		
			<input type="hidden" name="command" value="PlaceOrder" />

			<input type="hidden" name="photo" value="${THE_ITEM.base64Image}" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Name:</label></td>
						<td><input type="hidden" name="name" value="${THE_ITEM.name}" />
							<span>${THE_ITEM.name}</span></td>
					</tr>

					<tr>
						<td><label>Quantity:</label></td>
						<td><input style="width:35px;" name="quantity" ' value=1 onchange="myFunction(this.value)" type="number" id="myNumber"></td>
					</tr>

					<tr>
						<td><label>Price:</label></td>
						<td><input type="hidden" name="price" value="${THE_ITEM.price}" />
							<span>${THE_ITEM.price}</span></td>
					</tr>
					
					<tr>
						<td><label>Bill Amount:</label></td>
						<td><input type="hidden" name="billAmount" value="${THE_ITEM.price}" />
						  	<span id="billAmount">${THE_ITEM.price}</span></td>
					</tr>
					
				<tr>
						<td></td>
						<td> <img src="data:image/jpg;base64,${THE_ITEM.base64Image}" width="100" height="75"/> </td>
					</tr>
					
						<tr> 
					     <td><label>Delivery Address: </label></td>
					     <td><textarea rows='5' columns='30'></textarea></td>
					</tr>
										
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Place Order" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="items">Back to List</a>
		</p>
	</div>
	
	
<script>
	function myFunction(val) {
		document.getElementById("billAmount").innerHTML = val * ${THE_ITEM.price} ;
	}
</script>
</body>

</html>











