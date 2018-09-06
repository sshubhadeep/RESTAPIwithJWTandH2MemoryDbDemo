package com.sshubhadep.api.store;

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

import com.sshubhadep.api.filter.Secured;  
@Path("/StoreService")

public class StoreService {

	StoreDAO storeDAO = new StoreDAO(); 
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";

	@GET 
	@Path("/stores") 
	@Produces(MediaType.APPLICATION_XML) 
	public List<Store> getStores(){ 
		return storeDAO.getAllStores(); 
	}

	@GET
	@Path("/stores/{store_id}")
	@Produces(MediaType.APPLICATION_XML)
	public Store getStore(@PathParam("store_id") int storeid){
		return storeDAO.getStore(storeid);
	}

	@POST
	@Secured
	@Path("/stores")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createStore(
			@FormParam("id") int id,
			@FormParam("name") String name,
			@FormParam("city") String city,
			@Context HttpServletResponse servletResponse) throws IOException{

		Store store = new Store(id, name, city);
		int result = storeDAO.createStore(store);
		if(result == 1){
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}

	@PUT
	@Path("/stores")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateStore(@FormParam("id") int id,
			@FormParam("name") String name,
			@FormParam("city") String city,
			@Context HttpServletResponse servletResponse) throws IOException{
		Store store = new Store(id, name, city);
		int result = storeDAO.updateStore(store);
		if(result == 1){
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}

	@DELETE
	@Path("/stores/{store_id}")
	@Produces(MediaType.APPLICATION_XML)
	public String deleteStore(@PathParam("store_id") int store_id){
		int result = storeDAO.deleteStore(store_id);
		if(result == 1){
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}
}
