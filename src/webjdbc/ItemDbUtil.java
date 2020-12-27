package webjdbc;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.sql.DataSource;

public class ItemDbUtil {

	private DataSource dataSource;

	public ItemDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Item> getItems() throws Exception {
		
		List<Item> items = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from store1 order by name";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String name = myRs.getString("name");
				int quantity = myRs.getInt("quantity");
				double price = myRs.getDouble("price");
				Blob blob = myRs.getBlob("picture");
				
				String base64Image = null;
				 
				if (blob != null) {
					InputStream inputStream = blob.getBinaryStream();
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					byte[] buffer = new byte[4096];
					int bytesRead = -1;
					while ((bytesRead = inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, bytesRead);
					}
					byte[] imageBytes = outputStream.toByteArray();
					base64Image = Base64.getEncoder().encodeToString(imageBytes);
					inputStream.close();
					outputStream.close();
				}
				// create new student object
				Item tempItem = new Item(id, name, quantity, price, base64Image);
				
				// add it to the list of students
				items.add(tempItem);				
			}
			
			return items;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addItem(Item theItem) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into store1 "
					   + "(name, quantity, price, picture) "
					   + "values (?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1, theItem.getName());
			myStmt.setInt(2, theItem.getQuantity());
			myStmt.setDouble(3, theItem.getPrice());
			
			InputStream file = theItem.getInputStream();
			
			if (file != null) { 
				  
	                // Fetches the input stream 
	                // of the upload file for 
	                // the blob column 
	                myStmt.setBlob(4, file); 
	            } 
			
			// execute sql insert
			myStmt.executeUpdate();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Item getItem(String theItemId) throws Exception {

		Item theItem = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int itemId;
		
		try {
			// convert student id to int
			itemId = Integer.parseInt(theItemId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from store1 where id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, itemId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String name = myRs.getString("name");
				int quantity = myRs.getInt("quantity");
				double price = myRs.getDouble("price");
				
				Blob blob = myRs.getBlob("picture");
				
				String base64Image = null;
				 
				if (blob != null) {
					InputStream inputStream = blob.getBinaryStream();
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					byte[] buffer = new byte[4096];
					int bytesRead = -1;
					while ((bytesRead = inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, bytesRead);
					}
					byte[] imageBytes = outputStream.toByteArray();
					base64Image = Base64.getEncoder().encodeToString(imageBytes);
					inputStream.close();
					outputStream.close();
				}
				// use the studentId during construction
				theItem = new Item(itemId, name, quantity, price, base64Image);
			}
			else {
				throw new Exception("Could not find student id: " + itemId);
			}				
			
			return theItem;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

public void updatePicture(Item theItem) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update store1 "
						+ "set picture=? "
						+ "where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			
			InputStream file = theItem.getInputStream();
			
			if (file != null) { 
				  
	                // Fetches the input stream 
	                // of the upload file for 
	                // the blob column 
	                myStmt.setBlob(1, file); 
	            } 
			
			
			myStmt.setInt(2, theItem.getId());
			
			// execute SQL statement
			myStmt.executeUpdate();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

public void updateItems(Item theItem) throws Exception {
	
	Connection myConn = null;
	PreparedStatement myStmt = null;

	try {
		// get db connection
		myConn = dataSource.getConnection();
		
		// create SQL update statement
		String sql = "update store1 "
					+ "set name=?, quantity=?, price=?"
					+ "where id=?";
		
		// prepare statement
		myStmt = myConn.prepareStatement(sql);
		
		// set params
		myStmt.setString(1, theItem.getName());
		myStmt.setInt(2, theItem.getQuantity());
		myStmt.setDouble(3, theItem.getPrice());		
		myStmt.setInt(4, theItem.getId());
		
		// execute SQL statement
		myStmt.executeUpdate();
	}
	finally {
		// clean up JDBC objects
		close(myConn, myStmt, null);
	}
}

public void deleteItem(String theItemId) throws Exception {

	Connection myConn = null;
	PreparedStatement myStmt = null;
	
	try {
		// convert student id to int
		int itemId = Integer.parseInt(theItemId);
		
		// get connection to database
		myConn = dataSource.getConnection();
		
		// create sql to delete student
		String sql = "delete from store1 where id=?";
		
		// prepare statement
		myStmt = myConn.prepareStatement(sql);
		
		// set params
		myStmt.setInt(1, itemId);
		
		// execute sql statement
		myStmt.execute();
	}
	finally {
		// clean up JDBC code
		close(myConn, myStmt, null);
	}	
}


public void addOrder(Order theorder) throws Exception {

	Connection myConn = null;
	PreparedStatement myStmt = null;
	
	try {
		// get db connection
		myConn = dataSource.getConnection();
		
		// create sql for insert
		String sql = "insert into orders1 "
				   + "(customer_name, product_name, quantity, price, bill_amount) "
				   + "values (?, ?, ?, ?, ?)";
		
		myStmt = myConn.prepareStatement(sql);
		
		// set the param values for the student
		myStmt.setString(1, "unknown");
		myStmt.setString(2, theorder.getProductName());
		myStmt.setInt(3, theorder.getQuantity());
		myStmt.setDouble(4, theorder.getPrice());
		myStmt.setDouble(5, theorder.getBillAmount());
		
		/*
		 * InputStream file = theItem.getInputStream();
		 * 
		 * if (file != null) {
		 * 
		 * // Fetches the input stream // of the upload file for // the blob column
		 * myStmt.setBlob(5, file); }
		 */
		
		// execute sql insert
		myStmt.executeUpdate();
	}
	finally {
		// clean up JDBC objects
		close(myConn, myStmt, null);
	}
}

public List<Order> getOrders() throws Exception {
	
	List<Order> orders = new ArrayList<>();
	
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs = null;
	
	try {
		// get a connection
		myConn = dataSource.getConnection();
		
		// create sql statement
		String sql = "select * from orders1";
		
		myStmt = myConn.createStatement();
		
		// execute query
		myRs = myStmt.executeQuery(sql);
		
		// process result set
		while (myRs.next()) {
			
			// retrieve data from result set row
			int orderId = myRs.getInt("order_id");
			String customerName = myRs.getString("customer_name");
			int quantity = myRs.getInt("quantity");
			double price = myRs.getDouble("price");
			double billAmount = myRs.getDouble("bill_amount");
			String productName = myRs.getString("product_name");
			
			String base64Image = null;
			 
			/*
			 * if (blob != null) { InputStream inputStream = blob.getBinaryStream();
			 * ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); byte[]
			 * buffer = new byte[4096]; int bytesRead = -1; while ((bytesRead =
			 * inputStream.read(buffer)) != -1) { outputStream.write(buffer, 0, bytesRead);
			 * } byte[] imageBytes = outputStream.toByteArray(); base64Image =
			 * Base64.getEncoder().encodeToString(imageBytes); inputStream.close();
			 * outputStream.close(); }
			 */
			// create new student object
			Order tempOrder = new Order(orderId, customerName, productName, quantity, price, billAmount);
			
			// add it to the list of students
			orders.add(tempOrder);				
		}
		
		return orders;		
	}
	finally {
		// close JDBC objects
		close(myConn, myStmt, myRs);
	}		
}

}





