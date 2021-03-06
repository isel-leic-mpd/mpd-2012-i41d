package orm.northwind;

import java.util.Date;

import orm.Entity;

public class Employee implements Entity<Integer>{
	int employeeId;
	String title;
	String firstName;
	String lastName;
	Date birthDate;
	Iterable<Employee> employees; 
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getTitle() {
		return title;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Integer getId() {
		return employeeId;
	}

	@Override
	public void setId(Integer key) {
		employeeId = key;
	}

	
	public Employee(int id, String title, String firstName, String lastName, Date birthDate, Iterable<Employee> employees) {
		super();
		this.employeeId = id;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.employees  = employees;
	}

	public Iterable<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Iterable<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Employee [title=" + title + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate + "]";
	}
}
