<!DOCTYPE html>
<html>

<head>
	<title>Update Picture</title>
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
		<h3>Update Picture</h3>
		
		<form action="items" method="POST" enctype="multipart/form-data">
		
			<input type="hidden" name="command" value="UPDATEImage" />

			<input type="hidden" name="itemId" value="${THE_ITEM2.id}" />
			
			<table>
				<tbody>
					
 					<tr>
						<td></td>
						<td> <img src="data:image/jpg;base64,${THE_ITEM2.base64Image}" width="100" height="75"/> </td>
					</tr>
					
					<tr> 
					     <td><label>Item picture: </label></td>
					     <td><input type="file" name="photo" /></td>
					</tr>
										
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











