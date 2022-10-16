package CRS;

import java.io.*;
import java.util.*;

public class CRS {
	public static void main(String args[]){
		System.out.println("Welcome to CRS!");
		//admin created every time since info doesn't change and no relative info needs to be stored
		Admin admin = new Admin("Admin","Admin001");
		ArrayList<Student> students = new ArrayList<Student>();
		ArrayList<Course> courses = new ArrayList<Course>();
		Student s = null;
		User u = null;
		Data log = null;

		//check whether first time log-in
		File record = new File("data.ser");
		if (!record.exists()){
			log = new Data(courses,students);
			File file = new File("MyUniversityCourses.csv");
			ArrayList<String> raw_data = new ArrayList<String>();
			BufferedReader br = null;
			//read from the csv
			try { 
	            br = new BufferedReader(new FileReader(file));
	            String line; 
	            String header = br.readLine();
	            while ((line = br.readLine()) != null) { 
	                raw_data.add(line);
	            }
	        }catch (Exception e) {
	        	}finally{
	            if(br!=null){
	                try {
	                    br.close();
	                    br=null;
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
			//split lines of the cvs into arrayList of arrays of String
			ArrayList<String[]> split_data = new ArrayList<String[]>();
			for (String str : raw_data) {
				split_data.add(str.split(","));
			}
			
			//create course objects from the arraylist and store in the runtime course list
			for (int i = 0; i < split_data.size(); i++) {
				String[] spec = split_data.get(i);
				courses.add(new Course(spec[0],spec[1],Integer.parseInt(spec[2]),Integer.parseInt(spec[3]),new ArrayList<Student>(),spec[5],spec[6],spec[7]));
			}
		}
		else {
			//de-serialize if not first-time login, read from ser course list and students list, then store them into the runtime ArrayLists
			FileInputStream recordIn = null;
	         ObjectInputStream in = null;
			try{
		         recordIn = new FileInputStream("data.ser");
		         in = new ObjectInputStream(recordIn);
		         log = (Data)in.readObject();
		         courses = log.getCourses();
		         students = log.getStudents();
		         in.close();
		         recordIn.close();
		     }catch(IOException i){	
		    	 i.printStackTrace();
		    	}catch(ClassNotFoundException c)
		    {
		        System.out.println("Data not found");
		        c.printStackTrace();
		    }		
		}
		
		//login process
		Scanner input = new Scanner(System.in);
		
		Directory menu = new Directory();
		boolean falseid = false;
		
		do {
			do {
				menu.login();
				int option = input.nextInt();
				if (option==1) {
					//admin login
					while(true) {
						System.out.println("Please enter your username: ");
						String username = input.next();
						System.out.println("Please enter your password: ");
						String password = input.next();
						if (menu.adminLogin(admin,username,password)) {
							falseid = false;
							u = admin;
							u.setLogin(1);
							System.out.println("Login Success!");
							System.out.println("Here is your menu: ");
							break;
						}
						else {
							System.out.println("Wrong username or password!");
							System.out.println("Press 1 to enter again, press 2 to reselect identity");
							option = input.nextInt();
							if(option==2) {falseid = true; break;}
						}
					}
				}
				else if (option == 2) {
					//student login
					while(true) {
						System.out.println("Please enter your username: ");
						String username = input.next();
						System.out.println("Please enter your password: ");
						String password = input.next();
						if (menu.studentLogin(students,username,password)!=null) {
							falseid = false;
							s = menu.studentLogin(students,username,password);
							u = s;
							u.setLogin(1);
							System.out.println("Login Success!"); 
							System.out.println("Here is your menu: ");
							break;
						}
						else {
							System.out.println("Wrong username or password, please enter again!");
							System.out.println("Press 1 to enter again, press 2 to reselect identity");
							option = input.nextInt();
							if(option==2) {falseid = true; break;}
						}
					}
				}
				else if (option == 3) {
					//does not serialize if exit before login
					System.out.println("Exit Success!");
					System.exit(0);
				}
				else{
					//check wrong input
					System.out.println("Wrong input, please select again!");
					falseid = true;	
				}
			}while(falseid);
			
			
			if (u instanceof Admin) {
				//logged in as admin
				while(true) {
					//main menu for admin
					menu.adminMenu();
					int option = input.nextInt();
					if (option == 1) {
						while(true) {
							//course management menu for admin
							menu.adminCM();
							option = input.nextInt();
							if (option == 1) {
								System.out.println("Please enter Course name: ");
								String name = input.next();
								System.out.println("Please enter Course id: ");
								String id = input.next();
								System.out.println("Please enter maximum registration number: ");
								int max = input.nextInt();
								System.out.println("Please enter instructor: ");
								String instructor = input.next();
								System.out.println("Please enter section: ");
								String section = input.next();
								System.out.println("Please enter location: ");
								String location = input.next();
								Course c = new Course(name,id,max,0, null, instructor, section, location);
								admin.createCourse(c, courses);
							}
							else if (option == 2) {
								System.out.println("Please enter Course id: ");
								String id = input.next();
								System.out.println("Please enter section: ");
								String section = input.next();
								for (Course c : courses) {
									if(c.getId().equals(id)&&c.getSection().equals(section)) {
										admin.deleteCourse(c, courses);
										break;
									}
								}
							}
							else if (option == 3) {
								System.out.println("Please enter Course id: ");
								String id = input.next();
								System.out.println("Please enter section: ");
								String section = input.next();
								System.out.println("Please enter the property to be edited (max,instructor,section or location): ");
								String property = input.next();
								System.out.println("Please enter the value you want to change to: ");
								String value = input.next();
								for (Course c : courses) {
									if(c.getId().equals(id)&&c.getSection().equals(section)) {
										admin.editCourse(c, property,value);
										break;
									}
								}
							}
							else if (option == 4) {
								System.out.println("Please enter Course id: ");
								String id = input.next();
								for (Course c : courses) {
									if(c.getId().equals(id)) {
										admin.viewCourses(courses,id);
									}
								}
							}
							else if (option == 5) {
								System.out.println("Please enter student usernme: ");
								String username = input.next();
								System.out.println("Please enter student password: ");
								String password = input.next();
								System.out.println("Please enter student first name: ");
								String firstName = input.next();
								System.out.println("Please enter student last name: ");
								String lastName = input.next();
								admin.registerStudent(students, username, password, firstName, lastName);
							}
							else if (option == 6) {
								break;
							}
							else {
								System.out.println("Wrong input, please select again!");
							}	
						}
					}
					else if (option == 2) {
						while (true) {
							//report menu for admin
							menu.adminRP();
							option = input.nextInt();
							if (option == 1) {
								admin.viewCourses(courses);
							}
							else if (option == 2) {
								admin.viewFullCourse(courses);
								try {admin.writeFullCourse(courses);}catch(IOException e) {
									e.printStackTrace();
								}
							}
							else if (option == 3) {
								System.out.println("Please enter Course id: ");
								String id = input.next();
								System.out.println("Please enter section: ");
								String section = input.next();
								admin.viewNames(courses,id,section);
							}
							else if (option == 4) {
								System.out.println("Please enter student first name: ");
								String firstName = input.next();
								System.out.println("Please enter student last name: ");
								String lastName = input.next();
								admin.viewRegistered(students,firstName,lastName);
							}
							else if (option == 5) {
								admin.sortCourses(courses);
							}
							else if (option == 6) {
								break;
							}
							else {
								System.out.println("Wrong input, please select again!");
							}	
						}
					}
					else if (option == 3) {
						System.out.println("Logout Success!");
						//when logout, reset login status to 0, then serialize data and return to the login menu
						admin.setLogin(0);
						menu.serializaData(log);
						break;
					}
					else if (option == 4) {
						//when exit, reset login status to 0, then serialize data and exit system
						admin.setLogin(0);
						System.out.println("Exit Success!");
						menu.serializaData(log);
						System.exit(0);
					}
					//check wrong input
					else{
						System.out.println("Wrong input, please select again!");
					}
					
				}
			}
			else {
				while(true) {
					//main menu for student
					menu.studentMenu();
					int option = input.nextInt();
					if (option == 1) {
						while(true) {
							System.out.println();
							//student course management menu
							menu.stduentCM();
							option = input.nextInt();
							if (option == 1) {
								s.viewCourses(courses);
							}
							else if (option == 2) {
								s.viewAvailable(courses);
							}
							else if (option == 3) {
								System.out.println("Please enter Course id: ");
								String id = input.next();
								System.out.println("Please enter section: ");
								String section = input.next();
								for (Course c : courses) {
									if(c.getId().equals(id)&&c.getSection().equals(section)) {
										s.register(c);
										break;
									}
								}
							}
							else if (option == 4) {
								System.out.println("Please enter Course id: ");
								String id = input.next();
								System.out.println("Please enter section: ");
								String section = input.next();
								for (Course c : courses) {
									if(c.getId().equals(id)&&c.getSection().equals(section)) {
										s.withdraw(c);
										break;
									}
								}
							}
							else if (option == 5) {
								s.viewRegistered();
							}
							else if (option == 6) {
								break;
							}
							else {
								System.out.println("Wrong input, please select again!");
							}	
						}
					}
					else if (option == 2) {
						System.out.println("Logout Success!");
						s.setLogin(0);
						menu.serializaData(log);
						break;
					}
					else if (option == 3) {
						s.setLogin(0);
						System.out.println("Exit Success!");
						menu.serializaData(log);
						System.exit(0);
					}
				}
			}
		}while (u.getLogin()==0);
		input.close();
	}
}
