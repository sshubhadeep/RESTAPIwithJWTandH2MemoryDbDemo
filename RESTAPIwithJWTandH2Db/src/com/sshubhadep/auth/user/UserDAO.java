package com.sshubhadep.auth.user;

import java.util.ArrayList;
import java.util.List;

import com.sshubhadep.api.h2db.H2MemoryDatabase;

public class UserDAO {

	public List<User> getAllUsers(){

		List<User> userList = new ArrayList<User>();

		try{

			userList = H2MemoryDatabase.getAllUsers();

		} catch (Exception e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		}
		
		return userList;
	}

	public int saveUser(User user){

		try{

			H2MemoryDatabase.saveUser(user);

		} catch (Exception e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
			return -1;
		}

		return 1;
	}

	public int updateUser(User user) {

		List<User> userList = getAllUsers();

		for(User myUser : userList){
			if(myUser.getUserName() == user.getUserName()){
				H2MemoryDatabase.updateUser(user);
				return 1;
			}
		}		
		return -1;
	}

	public User getUser(String userName){
		List<User> userList = getAllUsers();

		for(User myUser : userList){
			if(myUser.getUserName().equalsIgnoreCase(userName)){
				return myUser;
			}
		}
		return null;
	}

	public int createUser(User user) {
		
		List<User> userList = getAllUsers();
		boolean userExists = false;

		for(User myUser : userList){
			if(myUser.getUserName().equalsIgnoreCase(user.getUserName())){
				userExists = true;
				break;
			}
		}
		
		if(!userExists){
			saveUser(user);
			return 1;
		}
		
		return -1;
	}
	
	public int deleteUser(String userName){
		List<User> userList = getAllUsers();

		for(User myUser : userList){
			if(myUser.getUserName().equalsIgnoreCase(userName)){
				deleteUser(myUser);
	            return 1;   
	         }
	      }		
	      return -1;
	   }

	public void deleteUser(User user) {
		
		try{

			H2MemoryDatabase.deleteUser(user);

		} catch (Exception e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		}		
	}
}
