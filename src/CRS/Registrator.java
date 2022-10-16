package CRS;

import java.util.ArrayList;

public interface Registrator {
	void viewCourses(ArrayList<Course> courses);
	void viewAvailable(ArrayList<Course> courses);
	void register(Course course);
	void withdraw(Course course);
	void viewRegistered();
}