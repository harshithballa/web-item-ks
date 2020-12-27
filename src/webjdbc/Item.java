package webjdbc;

import java.io.InputStream;
import java.sql.Blob;

public class Item {

	private int id;
	private String name;
	private int quantity;
	private double price;
	private String base64Image;
	private InputStream inputStream;
	

	public Item(String name, int quantity, double price) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}



	public Item(int id, String name, int quantity, double price) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	
	
	
	public Item(int id, String name, int quantity, double price, String picture) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.base64Image = picture;
	}
	


	public Item(String name, int quantity, double price, InputStream inputStream) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.inputStream = inputStream;
	}


	public Item(int id, InputStream inputStream) {
		super();
		this.id = id;
		this.inputStream = inputStream;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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



	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + "]";
	}

}
