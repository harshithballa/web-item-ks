<!DOCTYPE html>
<html>

<head>
	<title>Update Item</title>
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
		<h3>Update Item</h3>
		
		<form action="items" method="POST" enctype="multipart/form-data">
		
			<input type="hidden" name="command" value="UPDATE" />

			<input type="hidden" name="itemId" value="${THE_ITEM.id}" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Name:</label></td>
						<td><input type="text" name="name" 
								   value="${THE_ITEM.name}" /></td>
					</tr>

					<tr>
						<td><label>Quantity:</label></td>
						<td><input type="text" name="quantity" 
								   value="${THE_ITEM.quantity}" /></td>
					</tr>

					<tr>
						<td><label>Price:</label></td>
						<td><input type="text" name="price" 
								   value="${THE_ITEM.price}" /></td>
					</tr>
					
<%-- 					<tr>
						<td></td>
						<td> <img src="data:image/jpg;base64,${THE_ITEM.base64Image}" width="100" height="75"/> </td>
					</tr>
					
					<tr> 
					     <td><label>Item picture: </label></td>
					     <td><input type="file" name="photo" /></td>
					</tr> --%>
										
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="items?command=LIST">Back to List</a>
		</p>
	</div>
</body>

</html>











