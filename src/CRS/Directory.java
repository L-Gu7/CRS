package CRS;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
public class Directory {
	public Directory() {};
	
	public void login() {
		System.out.println("Please select your identity: ");
		System.out.println("1. Admin");
		System.out.println("2. Student");
		System.out.println("3. Exit System");
	}
	
	//since there is only one admin, check login info
	public boolean adminLogin(Admin admin,String username, String password) {
		if (username.equals(admin.getUsername())&& password.equals(admin.getPassword())) {
			return true;
		}
		return false;	
	}
	
	//check input user info from student list and return the corresponding student; return null if none matches
	public Student studentLogin(ArrayList<Student> students,String username, String password) {
		for (Student student : students) {
			if(student.getUsername().equals(username)&&student.getPassword().equals(password)) {
				return student;
			}
		}
		return null;
	}
	
	//all print menu methods
	public void mainMenu() {
		System.out.println("1. Courses Management");
		System.out.println("2. Reports");
		System.out.println("3. Log out");
		System.out.println("4. Exit System");
	}
	
	public void adminMenu() {
		System.out.println("1. Courses Management");
		System.out.println("2. Reports");
		System.out.println("3. Log out");
		System.out.println("4. Exit System");
	}
	
	public void adminCM() {
		System.out.println("Course Management");
		System.out.println("1. Create a new course");
		System.out.println("2. Delete a course");
		System.out.println("3. Edit a course");
		System.out.println("4. View a single course");
		System.out.println("5. Register a student");
		System.out.println("6. Back to the main menu");
	}
	
	public void adminRP() {
		System.out.println("Reports");
		System.out.println("1. View all courses");
		System.out.println("2. View full courses"); //write function is inserted
		System.out.println("3. View registered students");
		System.out.println("4. View students' courses");
		System.out.println("5. Sort number of registration");
		System.out.println("6. Back to the main menu");
	}
	
	public void studentMenu() {
		System.out.println("1. Courses Management");
		System.out.println("2. Log out");
		System.out.println("3. Exit System");
	}
	
	public void stduentCM() {
		System.out.println("Course Management");
		System.out.println("1. View all courses");
		System.out.println("2. View available courses");
		System.out.println("3. Register on a course");
		System.out.println("4. Withdraw from a course");
		System.out.println("5. View registered courses");
		System.out.println("6. Back to the main menu");
	}
	
	//serialize data after every logout or exit after login
	public void serializaData(Data log) {
		try {
			FileOutputStream serial = new FileOutputStream("data.ser");
			ObjectOutputStream output = new ObjectOutputStream(serial);
			output.writeObject(log);
			output.close();
			serial.close();
			System.out.println("Serialization Finished!");
			
		}catch(IOException i) {
			i.printStackTrace();
		}
	}
}
