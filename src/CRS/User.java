package CRS;

import java.util.*;

abstract class User {
	private String username;
	private String password;
	private int login;
	
	protected User() {}; 
	protected User(String username, String password) {
		username = this.username;
		password = this.password;
	}
	
	abstract void viewCourses(ArrayList<Course> courses);
	
	//abstract method declared to allow login status check as a general type 
	public int getLogin() {
		return login;
	}
	public void setLogin(int login) {
		login = this.login;
	}
}
