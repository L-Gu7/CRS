package CRS;
import java.util.*;
import java.io.*;

public class Data implements Serializable{
	private ArrayList<Course> courses;
	private ArrayList<Student> students;
	
	protected Data(ArrayList<Course> courses, ArrayList<Student> students) {
		this.courses = courses;
		this.students = students;
	}
	
	public ArrayList<Course> getCourses() {
		return courses;
	}
	
	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	
}
