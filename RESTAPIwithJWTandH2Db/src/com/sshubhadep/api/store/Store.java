package com.sshubhadep.api.store;

import java.io.Serializable;  
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement; 
@XmlRootElement(name = "store")

public class Store  implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	private int StoreID; 
	private String StoreName; 
	private String StoreCity;
	
	public Store(){} 
    
	public Store(int StoreID, String StoreName,String StoreCity){  
	      this.StoreID = StoreID; 
	      this.StoreName = StoreName; 
	      this.StoreCity = StoreCity;
	}

	public int getStoreID() {
		return StoreID;
	}
	@XmlElement
	public void setStoreID(int storeID) {
		StoreID = storeID;
	}

	public String getStoreName() {
		return StoreName;
	}
	@XmlElement
	public void setStoreName(String storeName) {
		StoreName = storeName;
	}

	public String getStoreCity() {
		return StoreCity;
	}
	@XmlElement
	public void setStoreCity(String storeCity) {
		StoreCity = storeCity;
	}

}
