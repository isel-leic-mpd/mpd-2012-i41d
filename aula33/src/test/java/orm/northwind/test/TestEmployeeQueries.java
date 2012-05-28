package orm.northwind.test;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;


import org.junit.Test;

import orm.DbAutoCommit;
import orm.JdbcExecutor;
import orm.JdbcExecutorSingleConnection;
import orm.northwind.Employee;
import orm.northwind.EmployeesDataMapper;
import app.CfgModule;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class TestEmployeeQueries {

	Injector inj;

	public TestEmployeeQueries(){
		inj = Guice.createInjector(
				Modules.override(new CfgModule()).with(new AbstractModule(){
					@Override
					protected void configure() {
						bind(JdbcExecutor.class).to(JdbcExecutorSingleConnection.class);
						bind(boolean.class).annotatedWith(DbAutoCommit.class).toInstance(false);
					}					
				}));
	}

	final static String sqlEmployees = "SELECT Title, FirstName, LastName, BirthDate FROM Employees"; 

	@Test
	public void testLoadAllEmployees() throws Exception{
		try(EmployeesDataMapper empMapper = inj.getInstance(EmployeesDataMapper.class)){
			Iterable<Employee> res = empMapper.loadAll();
			int count = 0;
			for (Employee employee : res) {
				count++;
			}
			Assert.assertEquals(count, 9);
		}
	}

	private static DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	@Test
	public void testLoadByIdEmployees()throws Exception{
		try(EmployeesDataMapper empMapper = inj.getInstance(EmployeesDataMapper.class)){
			Employee e = empMapper.loadById(7);
			Assert.assertEquals("Sales Representative", e.getTitle());
			Assert.assertEquals("Robert", e.getFirstName());
			Assert.assertEquals("King", e.getLastName());
			Assert.assertEquals(formatter.parse("29-5-1960"), e.getBirthDate());
		}
	}
	@Test
	public void testUpdateEmployee()throws Exception{
		try(EmployeesDataMapper empMapper = inj.getInstance(EmployeesDataMapper.class)){
			Employee e = empMapper.loadById(7);
			Assert.assertEquals("Sales Representative", e.getTitle());
			Assert.assertEquals("Robert", e.getFirstName());
			Assert.assertEquals("King", e.getLastName());
			Assert.assertEquals(formatter.parse("29-5-1960"), e.getBirthDate());
			//
			// Arrange
			//
			e.setBirthDate(formatter.parse("1-5-1974"));
			e.setFirstName("Joao");
			e.setLastName("Alberto");
			e.setTitle("Engenheiro");
			//
			// Act
			//		
			empMapper.update(e);
			//
			// Assert
			//
			e = empMapper.loadById(7);
			Assert.assertEquals("Engenheiro", e.getTitle());
			Assert.assertEquals("Joao", e.getFirstName());
			Assert.assertEquals("Alberto", e.getLastName());
			Assert.assertEquals(formatter.parse("1-5-1974"), e.getBirthDate());
		}
	}
}
