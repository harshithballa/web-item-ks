package webjdbc;
//services
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;


@WebServlet("/items")
@javax.servlet.annotation.MultipartConfig
public class ItemControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ItemDbUtil itemDbUtil;
	
	@Resource(name="jdbc/store")
	private DataSource dataSource;
	
	@Override 
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			itemDbUtil = new ItemDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			
			  if (theCommand == null) { theCommand = "DISPLAY"; }
			 
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listItems(request, response);
				break;
				
			case "LOAD":
				doPost(request, response);
				break;
				
			case "LoadImage":
				doPost(request, response);
				break;
				
			case "UPDATE":
				doPost(request, response);
				break;
				
			case "UPDATEImage":
				doPost(request, response);
				break;
				
			case "DELETE":
				doPost(request, response);
				break;
				
			case "DISPLAY":
				displayItems(request, response);
				break;
				
			case "BUY":
				buyItems(request, response);
				break;
				
			case "ORDERS":
				ordersList(request, response);
				break;
				
			default:
				displayItems(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // read the "command" parameter
            String theCommand = request.getParameter("command");
                    
            // route to the appropriate method
            switch (theCommand) {
                            
            case "ADD":
                addItems(request, response);
                break;
                
            case "LOAD":
				loadItems(request, response);
				break;
				
            case "LoadImage":
				loadImage(request, response);
				break;
				
            case "UPDATE":
				updateItems(request, response);
				break;
				
            case "UPDATEImage":
				updatePicture(request, response);
				break;
				
            case "DELETE":
				deleteItems(request, response);
				break;
				
            case "PlaceOrder":
            	placeOrder(request, response);
            	break;
                                
            default:
                listItems(request, response);
            }
                
        }
        catch (Exception exc) {
        	exc.printStackTrace();
            throw new ServletException(exc);
        }
        
    }
	
	private void deleteItems(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			String theItemId = request.getParameter("itemId");

			itemDbUtil.deleteItem(theItemId);
			
			// send them back to "list students" page
			//listItems(request, response);
			
			response.sendRedirect(request.getContextPath() + "/items?command=LIST");
		}
	
	
	private void updateItems(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student info from form data
			int id = Integer.parseInt(request.getParameter("itemId"));
			String name = request.getParameter("name");
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			double price = Double.parseDouble(request.getParameter("price"));
			

            Item theItem= new Item(id, name, quantity, price);
            // perform update on database
			itemDbUtil.updateItems(theItem);

			
			// send them back to the "list students" page
			//listItems(request, response);
			
			response.sendRedirect(request.getContextPath() + "/items?command=LIST");
			
		}
	
	private void updatePicture(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student info from form data
			String stId= request.getParameter("itemId");
			
			int id = Integer.parseInt(stId);
			
			InputStream inputStream = null;   
	        //String message = null; 

	        Part filePart = request.getPart("photo"); 
	  
	        if (filePart != null) { 
	        	
	            inputStream = filePart.getInputStream(); 

	        } 

            Item theItem= new Item(id, inputStream);
            // perform update on database
			itemDbUtil.updatePicture(theItem);

			
			// send them back to the "list students" page
			//listItems(request, response);
			
			response.sendRedirect(request.getContextPath() + "/items?command=LIST");
			
		}

	private void loadItems(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// read student id from form data
			String theItemId = request.getParameter("itemId");
			
			// get student from database (db util)
			Item theItem = itemDbUtil.getItem(theItemId);
			
			// place student in the request attribute
			request.setAttribute("THE_ITEM", theItem);
			
			// send to jsp page: update-student-form.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/update-item-form.jsp");
			dispatcher.forward(request, response);		
		}
	
	private void loadImage(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// read student id from form data
			String theItemId = request.getParameter("itemId");

			// get student from database (db util)
			Item theItem = itemDbUtil.getItem(theItemId);
			
			// place student in the request attribute
			request.setAttribute("THE_ITEM2", theItem);
			
			// send to jsp page: update-student-form.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/update-picture.jsp");
			dispatcher.forward(request, response);		
		}



	private void addItems(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student info from form data
		String name = request.getParameter("name");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		double price = Double.parseDouble(request.getParameter("price"));
		
		
		InputStream inputStream = null;   
        //String message = null; 

        Part filePart = request.getPart("photo"); 
  
        if (filePart != null) { 
        	
            inputStream = filePart.getInputStream(); 
        } 
  

		
		// create a new student object
		Item theItem = new Item(name, quantity, price, inputStream);
		
		// add the student to the database
		itemDbUtil.addItem(theItem);
				
		// send back to main page (the student list)
		//listStudents(request, response);
		
		// send back to main page (the student list)
        // SEND AS REDIRECT to avoid multiple-browser reload issue
        response.sendRedirect(request.getContextPath() + "/items?command=LIST");
	}
	

	private void listItems(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get students from db util
		List<Item> items = itemDbUtil.getItems();
		
		// add students to the request
		request.setAttribute("ITEM_LIST", items);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-items.jsp");
		dispatcher.forward(request, response);
	}
	
	private void ordersList(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// get students from db util
			List<Order> orders = itemDbUtil.getOrders();
			
			// add students to the request
			request.setAttribute("ORDER_LIST", orders);
					
			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/list-orders.jsp");
			dispatcher.forward(request, response);
		}
	
	private void displayItems(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// get students from db util
			List<Item> items = itemDbUtil.getItems();
			
			// add students to the request
			request.setAttribute("ITEM_LIST", items);
					
			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/display-items.jsp");
			dispatcher.forward(request, response);
		}
	
	
	private void placeOrder(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

		/*
		 * // get students from db util List<Item> items = itemDbUtil.getItems();
		 * 
		 * // add students to the request request.setAttribute("ITEM_LIST", items);
		 */
		// read student info from form data
		
				String productName = request.getParameter("name");
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				double price = Double.parseDouble(request.getParameter("price"));
				double billAmount = Double.parseDouble(request.getParameter("billAmount"));
				
				InputStream inputStream = null;   
		        //String message = null; 

		        Part filePart = request.getPart("photo"); 
		  
		        if (filePart != null) { 
		        	
		            inputStream = filePart.getInputStream(); 
		        } 
		  

				
				// create a new student object
				Order theOrder = new Order(productName, quantity, price, billAmount, inputStream);
				
				// add the student to the database
				itemDbUtil.addOrder(theOrder);
					
			
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/orderPlacedConfirm.jsp");
			dispatcher.forward(request, response);
		}
	
	private void buyItems(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// read student id from form data
			String theItemId = request.getParameter("itemId");
			
			// get student from database (db util)
			Item theItem = itemDbUtil.getItem(theItemId);
			
			// place student in the request attribute
			request.setAttribute("THE_ITEM", theItem);
			
			// send to jsp page: update-student-form.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/buy-item-form.jsp");
			dispatcher.forward(request, response);		
		}

}





