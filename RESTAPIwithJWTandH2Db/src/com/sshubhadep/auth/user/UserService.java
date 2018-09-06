package com.sshubhadep.auth.user;

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

import com.sshubhadep.auth.jwt.JWTCreator;  
@Path("/UserService")

public class UserService {

	UserDAO userDAO = new UserDAO(); 
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";
	private static final String SUCCESS_RESULT_LOGIN="<result>User Successfully Logged In.</result>";
	private static final String FAILURE_RESULT_LOGIN="<result>Invalid UserName or Password</result>";
	private static final String FAILURE_RESULT_LOGIN_JWT="<result>Unable to aquire jwt token. Please try again.</result>";
	private static final String SUCCESS_RESULT_REGISTRATION="<result>User Successfully Registered.</result>";
	private static final String FAILURE_RESULT_REGISTRATION="<result>Unsuccessful. Try Changing UserName.</result>";

	@GET 
	@Path("/users") 
	@Produces(MediaType.APPLICATION_XML) 
	public List<User> getUsers(){ 
		return userDAO.getAllUsers(); 
	}

	@GET
	@Path("/users/{userName}")
	@Produces(MediaType.APPLICATION_XML)
	public User getUser(@PathParam("userName") String userName){
		return userDAO.getUser(userName);
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String login(@FormParam("UserName") String UserName,
			@FormParam("Password") String Password,
			@Context HttpServletResponse servletResponse) throws IOException{
		
		User user = userDAO.getUser(UserName);
		if(user == null || !user.getPassword().equals(Password))
			return FAILURE_RESULT_LOGIN;
		
		String jwt = JWTCreator.generateJWT(user);
		if(jwt == null || jwt.length() < 5)
			return FAILURE_RESULT_LOGIN_JWT;
		System.out.println("JWT created : "+jwt);
		servletResponse.setHeader("jwt", jwt);
		return SUCCESS_RESULT_LOGIN;
	}

	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String register(
			@FormParam("FirstName") String FirstName,
			@FormParam("LastName") String LastName,
			@FormParam("Age") int Age,
			@FormParam("Gender") String Gender,
			@FormParam("UserName") String UserName,
			@FormParam("Password") String Password,
			@Context HttpServletResponse servletResponse) throws IOException{
		
		List<User> userList = userDAO.getAllUsers();
		int userId = 1;
		if(userList.size() > 0)
			userId += userList.size();

		User user = new User(userId,FirstName,LastName,Age,Gender,UserName,Password);
		int result = userDAO.createUser(user);
		if(result == 1){
			return SUCCESS_RESULT_REGISTRATION;
		}
		return FAILURE_RESULT_REGISTRATION;
	}

	@PUT
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateUser(
			@FormParam("FirstName") String FirstName,
			@FormParam("LastName") String LastName,
			@FormParam("Age") int Age,
			@FormParam("Gender") String Gender,
			@FormParam("UserName") String UserName,
			@FormParam("Password") String Password,
			@Context HttpServletResponse servletResponse) throws IOException{
		
		User user = userDAO.getUser(UserName);
		if(user == null)
			return FAILURE_RESULT;
		
		user.setFirstName(FirstName);
		user.setLastName(LastName);
		user.setAge(Age);
		user.setGender(Gender);
		user.setPassword(Password);
		
		int result = userDAO.updateUser(user);
		if(result == 1){
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}

	@DELETE
	@Path("/users/{userName}")
	@Produces(MediaType.APPLICATION_XML)
	public String deleteUser(@PathParam("userName") String userName){
		int result = userDAO.deleteUser(userName);
		if(result == 1){
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}
}
