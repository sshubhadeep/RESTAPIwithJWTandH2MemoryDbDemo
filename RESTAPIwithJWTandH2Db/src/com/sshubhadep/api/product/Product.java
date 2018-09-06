package com.sshubhadep.api.product;

import java.io.Serializable;  
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement; 
@XmlRootElement(name = "product")

public class Product  implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	private int ProductID; 
	private String ProductName; 
	private String ProductSport;
	private int ProductLevel; 
	private String ProductDescription; 
	private String AssociatedStores;
	
	public Product(){} 
    
	public Product(int ProductID, String ProductName,String ProductSport,int ProductLevel,String ProductDescription,String AssociatedStores){  
	      this.ProductID = ProductID; 
	      this.ProductName = ProductName; 
	      this.ProductSport = ProductSport;
	      
	      this.ProductLevel = ProductLevel; 
	      this.ProductDescription = ProductDescription; 
	      this.AssociatedStores = AssociatedStores; 
	}
	
	public int getProductID() {
		return ProductID;
	}
	@XmlElement
	public void setProductID(int productID) {
		ProductID = productID;
	}
	public String getProductName() {
		return ProductName;
	}
	@XmlElement
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public String getProductSport() {
		return ProductSport;
	}
	@XmlElement
	public void setProductSport(String productSport) {
		ProductSport = productSport;
	}
	public int getProductLevel() {
		return ProductLevel;
	}
	@XmlElement
	public void setProductLevel(int productLevel) {
		ProductLevel = productLevel;
	}
	public String getProductDescription() {
		return ProductDescription;
	}
	@XmlElement
	public void setProductDescription(String productDescription) {
		ProductDescription = productDescription;
	}
	public String getAssociatedStores() {
		return AssociatedStores;
	}
	@XmlElement
	public void setAssociatedStores(String associatedStores) {
		AssociatedStores = associatedStores;
	}
	

}
