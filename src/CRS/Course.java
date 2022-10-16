package CRS;
import java.util.*;
import java.io.*;
public class Course implements Comparable<Course>, Serializable{
	private String name;
	private String id;
	private int max;
	private int current;
	//arrayList to store the students that have registered in the course
	private ArrayList<Student> students;
	private String instructor;
	private String section;
	private String location;
	
	protected Course() {};
	protected Course(String name, String id, int max, int current, ArrayList<Student> students, String instructor,
			String section, String location) {
		this.name = name;
		this.id = id;
		this.max = max;
		this.current = current;
		this.students = students;
		this.instructor = instructor;
		this.section = section;
		this.location = location;
	}
	
	//to determine if this course is full/available
	public boolean isFull() {
		if (current == max) {
			return true;
		}
		return false;
	}
	
	//register student into this course: add student to the student list and increase the current number
	public void addStudent(Student student) {
		students.add(student);
		current++;
	}
	
	//delete student from the course: delete student from the student list and decrease the current number
	public void deleteStudent(Student student) {
		students.remove(student);
		current--;
	}
	
	//getters and setters
	//no setter for name or id since these cannot be modified once a course is added
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getCurrent() {
		return current;
	}
	
	//no setter for student_names since it can be modified only by adding and deleting to the arrayList
	public ArrayList<String> getNames() {
		ArrayList<String> names = new ArrayList<String>();
		for (Student student:students) {
			names.add(student.getFirstName());
		}
		return names;
	}
	
	public ArrayList<Student> getStudents() {
		return students;
	}

	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	// Implements comparable interface to enable sorting according to registered number of a course
	public int compareTo(Course c) {
		if (this.getCurrent()>c.getCurrent()) {
			return -1;
		}
		else if (this.getCurrent()<c.getCurrent()) {
			return 1;
		}
		return 0;
	}
}
