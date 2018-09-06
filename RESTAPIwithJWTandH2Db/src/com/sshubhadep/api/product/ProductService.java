package com.sshubhadep.api.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;  
@Path("/ProductService")

public class ProductService {

	ProductDAO productDAO = new ProductDAO();
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";
	
	@GET 
	@Path("/products") 
	@Produces(MediaType.APPLICATION_XML) 
	public List<Product> getProducts(){ 
		return productDAO.getAllProducts(); 
	}
	
	@GET
	@Path("/products/{product_id}")
	@Produces(MediaType.APPLICATION_XML)
	public Product getProduct(@PathParam("product_id") int product_id){
		return productDAO.getProduct(product_id);
	}

	@POST
	@Path("/products")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createProduct(
			@FormParam("ProductID") int ProductID,
			@FormParam("ProductName") String ProductName,
			@FormParam("ProductSport") String ProductSport,
			@FormParam("ProductLevel") int ProductLevel,
			@FormParam("ProductDescription") String ProductDescription,
			@FormParam("AssociatedStores") String AssociatedStores,
			@Context HttpServletResponse servletResponse) throws IOException{

		Product product = new Product(ProductID, ProductName, ProductSport, ProductLevel, ProductDescription, AssociatedStores);
		int result = productDAO.createProduct(product);
		if(result == 1){
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}

	@PUT
	@Path("/products")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateProduct(@FormParam("id") int id,
			@FormParam("ProductID") int ProductID,
			@FormParam("ProductName") String ProductName,
			@FormParam("ProductSport") String ProductSport,
			@FormParam("ProductLevel") int ProductLevel,
			@FormParam("ProductDescription") String ProductDescription,
			@FormParam("AssociatedStores") String AssociatedStores,
			@Context HttpServletResponse servletResponse) throws IOException{
		Product product = new Product(ProductID, ProductName, ProductSport, ProductLevel, ProductDescription, AssociatedStores);
		int result = productDAO.updateProduct(product);
		if(result == 1){
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}
	
	@DELETE
	@Path("/products/{product_id}")
	@Produces(MediaType.APPLICATION_XML)
	public String deleteProduct(@PathParam("product_id") int product_id){
		int result = productDAO.deleteProduct(product_id);
		if(result == 1){
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}
}
