package CRS;

import java.io.IOException;
import java.util.ArrayList;

public interface Administrator {
	void createCourse(Course new_course, ArrayList<Course> courses);
	void deleteCourse(Course course_to_del, ArrayList<Course> courses);
	void editCourse(Course course_to_edit, String property, String value);
	void viewCourses(ArrayList<Course> courses, String id);
	void registerStudent(ArrayList<Student> students, String username, String password, String firstName, String LastName) ;
	void viewCourses(ArrayList<Course> courses);
	void viewFullCourse(ArrayList<Course> courses);
	void writeFullCourse(ArrayList<Course> courses)throws IOException;
	void viewNames(ArrayList<Course> courses, String id, String section);
	void viewRegistered(ArrayList<Student> students, String firstname, String lastname);
	void sortCourses(ArrayList<Course> courses);
}
