package orm.northwind.test;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import junit.framework.Assert;

import model.Employee;

import org.junit.Test;

import orm.JdbcExecutor;
import orm.northwind.EmployeeLoadByAll;
import orm.northwind.EmployeeLoadByBirthDate;
import orm.northwind.EmployeeLoadById;
import app.CfgModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class TestEmployeeQueries {
	
	JdbcExecutor db;
	
	public TestEmployeeQueries(){
		Injector inj = Guice.createInjector(new CfgModule());
		db = inj.getInstance(JdbcExecutor.class);
	}
	
	@Test
	public void testLoadAllEmployees() throws SQLException{
		EmployeeLoadByAll loadAll = new EmployeeLoadByAll();
		Iterable<Employee> res = db.executeCmd(loadAll);
		int count = 0;
		for (Employee employee : res) {
			count++;
		}
		Assert.assertEquals(count, 9);
	}
	
	private static DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	
	@Test
	public void testLoadByIdEmployees()throws SQLException, ParseException{
		EmployeeLoadById loadById = new EmployeeLoadById();
		Employee e = db.executeCmd(loadById, "7");
		Assert.assertEquals("Sales Representative", e.getTitle());
		Assert.assertEquals("Robert", e.getFirstName());
		Assert.assertEquals("King", e.getLastName());
		Assert.assertEquals(formatter.parse("29-5-1960"), e.getBirthDate());
	}
	@Test
	public void testLoadByDate()throws SQLException{
		EmployeeLoadByBirthDate loadByDate = new EmployeeLoadByBirthDate();
		Iterable<Employee> res = db.executeCmd(loadByDate, "01-01-1960");
		int count = 0;
		for (Employee employee : res) {
			count++;
		}
		Assert.assertEquals(count, 4);

	}
}
