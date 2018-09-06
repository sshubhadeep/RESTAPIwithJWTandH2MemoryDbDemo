package com.sshubhadep.auth.user;

import java.io.Serializable;  
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement; 
@XmlRootElement(name = "user")

public class User  implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	private int UserID; 
	private String FirstName; 
	private String LastName;
	private int Age; 
	private String Gender; 
	private String UserName;
	private String Password;
	
	public User(){}
	
	public User(int userID, String firstName, String lastName, int age, String gender, String userName,
			String password) {
		UserID = userID;
		FirstName = firstName;
		LastName = lastName;
		Age = age;
		Gender = gender;
		UserName = userName;
		Password = password;
	}

	public int getUserID() {
		return UserID;
	}
	@XmlElement
	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getFirstName() {
		return FirstName;
	}

	@XmlElement
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}
	@XmlElement
	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public int getAge() {
		return Age;
	}
	@XmlElement
	public void setAge(int age) {
		Age = age;
	}

	public String getGender() {
		return Gender;
	}
	@XmlElement
	public void setGender(String gender) {
		Gender = gender;
	}

	public String getUserName() {
		return UserName;
	}
	@XmlElement
	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}
	@XmlElement
	public void setPassword(String password) {
		Password = password;
	}

}
