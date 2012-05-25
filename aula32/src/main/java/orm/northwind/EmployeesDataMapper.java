package orm.northwind;

import java.sql.SQLException;

import com.google.inject.Inject;

import orm.JdbcBinder;
import orm.JdbcCmdQuery;
import orm.JdbcExecutor;

public class EmployeesDataMapper {

	final static String sqlEmployees = "SELECT Title, FirstName, LastName, BirthDate FROM Employees";
	
	final JdbcExecutor exec;

	@Inject
	public EmployeesDataMapper(JdbcExecutor exec) {
		this.exec = exec;
	}
	JdbcCmdQuery<Iterable<Employee>> cmdLoadAll = new JdbcCmdQuery<Iterable<Employee>>(
			sqlEmployees, 
			JdbcConverterEmployee.SIGLETON);

	JdbcCmdQuery<Iterable<Employee>> cmdLoadById = new JdbcCmdQuery<Iterable<Employee>>(
			sqlEmployees + " WHERE EmployeeId = ?", 
			JdbcConverterEmployee.SIGLETON, 
			JdbcBinder.IntBinder
			);

	JdbcCmdQuery<Iterable<Employee>> cmdLoadByDate = new JdbcCmdQuery<Iterable<Employee>>(
			sqlEmployees + " WHERE BirthDate > ?", 
			JdbcConverterEmployee.SIGLETON, 
			JdbcBinder.DateBinder
			);


	public Iterable<Employee> loadAll() throws SQLException{
		return exec.executeCmd(cmdLoadAll); 

	}
	public Iterable<Employee> loadById(String id)throws SQLException{
		return exec.executeCmd(cmdLoadById, id);
	}
	public Iterable<Employee> loadByDate(String date) throws SQLException{
		return exec.executeCmd(cmdLoadByDate, date);
	}

}
