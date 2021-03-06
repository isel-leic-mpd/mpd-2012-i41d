package orm.northwind;

import java.util.Date;

public class Employee {
	final String title;
	final String firstName;
	final String lastName;
	final Date birthDate;
	
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

	public Employee(String title, String firstName, String lastName, Date birthDate) {
		super();
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Employee [title=" + title + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate + "]";
	}
}
