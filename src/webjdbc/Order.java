package webjdbc;

import java.io.InputStream;

public class Order {
	
	private int orderId;
	private String customerName;
	private String productName;
	private int quantity;
	private double price;
	private double billAmount;
	private String billAddress;
	private String base64Image;
	private InputStream inputStream;
	
	
	public Order(int orderId, String customerName, String productName, int quantity, double price, double billAmount,
			String billAddress, String base64Image, InputStream inputStream) {
		super();
		this.orderId = orderId;
		this.customerName = customerName;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.billAmount = billAmount;
		this.billAddress = billAddress;
		this.base64Image = base64Image;
		this.inputStream = inputStream;
	}
	
	
	
	public Order(String productName, int quantity, double price, double billAmount, InputStream inputStream) {
		super();
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.billAmount = billAmount;
		this.inputStream = inputStream;
	}
	

	public Order(int orderId, String customerName, String productName, int quantity, double price, double billAmount) {
		super();
		this.orderId = orderId;
		this.customerName = customerName;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.billAmount = billAmount;
	}



	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}
	public String getBillAddress() {
		return billAddress;
	}
	public void setBillAddress(String billAddress) {
		this.billAddress = billAddress;
	}
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	
}
