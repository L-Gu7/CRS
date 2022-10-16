package CRS;

import java.util.*;
import java.io.*;

public class Admin extends User implements Administrator, Serializable{
	private String username;
	private String password;
	
	protected Admin(String username, String password) {
		this.username = username;
		this.password = password;
		super.setLogin(0);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public void createCourse(Course new_course, ArrayList<Course> courses) {
		courses.add(new_course);
	}

	@Override
	public void deleteCourse(Course course_to_del, ArrayList<Course> courses) {
		courses.remove(course_to_del);	
	}

	@Override
	public void editCourse(Course course_to_edit, String property, String value) {
		switch(property) {
		case "max": course_to_edit.setMax(Integer.parseInt(value)); break;
		case "instructor": course_to_edit.setInstructor(value); break;
		case "section": course_to_edit.setSection(value); break;
		case "location": course_to_edit.setLocation(value); break;
		}
	}
	
	//show info of a single course
	@Override
	public void viewCourses(ArrayList<Course> courses, String id) {
		for (Course c: courses) {
			if (c.getId().equals(id)) {
				System.out.println(c.getName());
				System.out.println("ID: "+c.getId());
				System.out.println("Section "+c.getSection());
				System.out.println("Instructor: "+c.getInstructor());
				System.out.println("Location: "+c.getLocation());
				System.out.println("\n");
			}
		}
	}
	
	//create a new student
	@Override
	public void registerStudent(ArrayList<Student> students, String username, String password, String firstName, String LastName) {
		students.add(new Student(username,password,firstName,LastName));
	}

	//show info of all courses
	@Override
	public void viewCourses(ArrayList<Course> courses) {
		for (Course c: courses) {
				System.out.println(c.getName());
				System.out.println("ID: "+c.getId());
				System.out.println("Section "+c.getSection());
				System.out.println("Current Registered: "+c.getCurrent());
				System.out.println("Max Capacity: "+c.getMax());
				System.out.println("\n");
		}
	}

	@Override
	//show the id and section of courses that are already full
	public void viewFullCourse(ArrayList<Course> courses) {
		for (Course c: courses) {
			if (c.getMax()==c.getCurrent()) {
				System.out.println(c.getId()+" Section"+c.getSection());
			}
		}
	}
	
	//separate io method to write full courses in an txt file
	@Override
	public void writeFullCourse(ArrayList<Course> courses) throws IOException{
		File full = new File("full.txt");
		if (!full.exists()) {
			full.createNewFile();
		}
		try{
		BufferedWriter bw =new BufferedWriter(new FileWriter(full));
		bw.write("Full Courses: ");
		for (Course c: courses) {
			if (c.getMax()==c.getCurrent()) {
				bw.write("s");
				bw.write(c.getId()+" Section"+c.getSection());
			}
		}
		bw.flush();
		bw.close();
		}catch(IOException e){
		System.out.println(e);
		}
	}
	
	//view students that have registered in a course
	@Override
	public void viewNames(ArrayList<Course> courses, String id, String section) {
		for (Course c: courses) {
			if (c.getId().equals(id) && c.getSection().equals(section)) {
				if (c.getStudents().size()==0) {
					System.out.println("No students have registered to this class yet.");
				}else {
					System.out.println("Registered students: ");
					for (String name : c.getNames()) {
						System.out.println(name);
					}
				}
			}
		}
	}
	
	////view the courses students have registered in
	@Override
	public void viewRegistered(ArrayList<Student> students, String firstname, String lastname) {
		for (Student s: students) {
			if (s.getFirstName().equals(firstname) && s.getLastName().equals(lastname)) {
				if (s.getRegistered().size()!=0) {
					for (Course c : s.getRegistered()) {
						System.out.println(c.getId()+" Section"+c.getSection());
					}
				}else {
					System.out.println("This student has not registered to any classes yet.");
				}
			}
			break;
		}
	}
	
	//sort courses by the current number of registered students(using comparable)
	@Override
	public void sortCourses(ArrayList<Course> courses) {
		Course[] sorted = new Course[courses.size()];
		for (int i = 0; i<courses.size();i++) {
			sorted[i] = courses.get(i);
		}
		Arrays.sort(sorted);
		for (Course c: sorted) {
			System.out.println(c.getId()+" Section"+c.getSection());
			System.out.println("Registered number: "+c.getCurrent());
			System.out.println("\n");
		}
	}
}
