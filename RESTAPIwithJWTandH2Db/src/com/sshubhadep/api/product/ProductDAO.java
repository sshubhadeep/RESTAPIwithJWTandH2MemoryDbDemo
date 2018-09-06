package com.sshubhadep.api.product;

import java.util.ArrayList;
import java.util.List;

import com.sshubhadep.api.h2db.H2MemoryDatabase;

public class ProductDAO {
	
	public List<Product> getAllProducts(){
		
		List<Product> productList = new ArrayList<Product>();
	
		try{
			
			productList = H2MemoryDatabase.getAllProducts();
			
		} catch (Exception e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		}
		
		return productList;
	}
	
	public void saveProduct(Product product){
		
		try{
			
			H2MemoryDatabase.saveProduct(product);
			
		} catch (Exception e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		}
	}

	public Product getProduct(int product_id) {
		List<Product> products = getAllProducts();

		for(Product product: products){
			if(product.getProductID() == product_id){
				return product;
			}
		}
		return null;
	}

	public int createProduct(Product product) {
		List<Product> products = getAllProducts();
		boolean productExists = false;

		for(Product myproduct: products){
			if(product.getProductID() == myproduct.getProductID()){
				productExists = true;
				break;
			}
		}
		
		if(!productExists){
			saveProduct(product);
			return 1;
		}
		
		return -1;
	}

	public int updateProduct(Product product) {
		
		List<Product> products = getAllProducts();

		for(Product myproduct: products){
			if(product.getProductID() == myproduct.getProductID()){
				H2MemoryDatabase.updateProduct(product);
				return 1;
			}
		}		
		return -1;
	}

	public int deleteProduct(int id){
		List<Product> products = getAllProducts();

		for(Product myproduct: products){
			if(myproduct.getProductID() ==  id){
	            deleteProduct(myproduct);
	            return 1;   
	         }
	      }		
	      return -1;
	   }

	private void deleteProduct(Product myproduct) {
		
		try{

			H2MemoryDatabase.deleteProduct(myproduct);

		} catch (Exception e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		}
		
	}

}
