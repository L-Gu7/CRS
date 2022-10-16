package CRS;
import java.util.ArrayList;
import java.io.*;

public class Student extends User implements Registrator, Serializable{
	private String username;
	private String password;
	private String firstName;
	private String LastName;
	private ArrayList<Course> registered = new ArrayList<Course>();
	
	protected Student() {}
	
	protected Student(String username, String password, String firstName, String LastName) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.LastName = LastName;
		super.setLogin(0);
	}
	
	//In my design, students can only see the status of the class instead of the exact numbers
	@Override
	public void viewCourses(ArrayList<Course> courses) {
		for (Course c: courses) {
			System.out.println(c.getName());
			System.out.println("ID: "+c.getId());
			System.out.println("Section "+c.getSection());
			System.out.print("Registration Status: ");
			if (c.isFull()) {
				System.out.print("Full\n");
			}
			else {
				System.out.print("Available\n");
			}
			System.out.println("\n");
		}
	}
	
	//to show available courses for the student user
	@Override
	public void viewAvailable(ArrayList<Course> courses) {
		ArrayList<Course> available = new ArrayList<Course>();
		for (Course c: courses) {
			if (!c.isFull()) {
				available.add(c);
			}
		}
		if (available.size()==0) {
			System.out.println("No courses are available for now!");
		}else {System.out.println("Available courses: ");
			for (Course c:courses) {
				System.out.println(c.getName()+" Section"+c.getSection());
				System.out.println("\n");
			}
		}
	}
	
	//register into a course by adding the student in the student list of the course and add the course to the student's course list
	@Override
	public void register(Course course) {
		this.getRegistered().add(course);
		course.addStudent(this);
	}
	
	//withdraw a course by deleting the student from the student list of the course and delete the course from the student's course list
	@Override
	public void withdraw(Course course) {
		this.getRegistered().remove(course);
		course.deleteStudent(this);
	}
	
	//to show the courses the student has registered in, prompt reminder when none is registered
	@Override
	public void viewRegistered() {
		if (this.getRegistered().size()==0) {
			System.out.println("You haven't registered in any courses.");
		}
		else {
			System.out.println("Registered courses:");
			for(Course c :this.getRegistered()){
				System.out.println(c.getName()+" Section"+c.getSection());
			}
		}
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return LastName;
	}
	
	//no setter for registered since courses can only be added or deleted on by one
	public ArrayList<Course> getRegistered() {
		return registered;
	}
}
