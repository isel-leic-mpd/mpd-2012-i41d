package orm.northwind;

import orm.Entity;

public class Product implements Entity<Integer>{
	public Product(int productID, String productName, double unitPrice,
			int unitsInStock) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.unitsInStock = unitsInStock;
	}
	int productID;
	String productName;
	double unitPrice;
	int unitsInStock;
	
	public Integer getId() {
		return productID;
	}
	public void setId(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getUnitsInStock() {
		return unitsInStock;
	}
	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}
	
}
