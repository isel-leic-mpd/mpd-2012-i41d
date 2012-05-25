package orm.northwind.test;

import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;


import org.junit.Test;

import orm.JdbcBinder;
import orm.JdbcCmdQuery;
import orm.JdbcConverter;
import orm.JdbcExecutor;
import orm.northwind.Employee;
import orm.northwind.EmployeesDataMapper;
import orm.northwind.JdbcConverterEmployee;
import app.CfgModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class TestEmployeeQueries {

	EmployeesDataMapper empMapper;

	public TestEmployeeQueries(){
		Injector inj = Guice.createInjector(new CfgModule());
		empMapper = inj.getInstance(EmployeesDataMapper.class);
	}

	final static String sqlEmployees = "SELECT Title, FirstName, LastName, BirthDate FROM Employees"; 

	@Test
	public void testLoadAllEmployees() throws SQLException{
		Iterable<Employee> res = empMapper.loadAll();
		int count = 0;
		for (Employee employee : res) {
			count++;
		}
		Assert.assertEquals(count, 9);
	}

	private static DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	@Test
	public void testLoadByIdEmployees()throws SQLException, ParseException{
		Employee e = empMapper.loadById("7").iterator().next();
		Assert.assertEquals("Sales Representative", e.getTitle());
		Assert.assertEquals("Robert", e.getFirstName());
		Assert.assertEquals("King", e.getLastName());
		Assert.assertEquals(formatter.parse("29-5-1960"), e.getBirthDate());
	}
	@Test
	public void testLoadByDate()throws SQLException{
		Iterable<Employee> res = empMapper.loadByDate("01-01-1960");
		int count = 0;
		for (Employee employee : res) {
			count++;
		}
		Assert.assertEquals(count, 4);

	}
}
