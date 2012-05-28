package orm.northwind;

import java.sql.SQLException;

import com.google.inject.Inject;

import orm.DataMapper;
import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcCmdQuery;
import orm.JdbcCmdUpdate;
import orm.JdbcExecutor;
import orm.JdbcExecutorMultipleConnection;

public class EmployeesDataMapper implements DataMapper<Integer, Employee>, AutoCloseable{

	final static String sqlEmployees = "SELECT EmployeeId, Title, FirstName, LastName, BirthDate FROM Employees";
	final static String sqlUpdateEmployee = "UPDATE Employees SET Title = ?, FirstName = ?, LastName = ?, BirthDate = ? WHERE EmployeeId = ?";
	final JdbcExecutor exec;

	@Inject
	public EmployeesDataMapper(JdbcExecutor exec) {
		this.exec = exec;
	}
	JdbcCmd<Iterable<Employee>> cmdLoadAll = new JdbcCmdQuery<Iterable<Employee>>(
			sqlEmployees, 
			JdbcConverterEmployee.SIGLETON);

	JdbcCmd<Iterable<Employee>> cmdLoadById = new JdbcCmdQuery<Iterable<Employee>>(
			sqlEmployees + " WHERE EmployeeId = ?", 
			JdbcConverterEmployee.SIGLETON, 
			JdbcBinder.IntBinder
			);

	JdbcCmd<Employee> cmdUpdate = new JdbcCmdUpdate<Employee>(
			sqlUpdateEmployee, 
			JdbcBinder.StringBinder,
			JdbcBinder.StringBinder,
			JdbcBinder.StringBinder,
			JdbcBinder.DateBinder,
			JdbcBinder.IntBinder); 

	@Override
	public Iterable<Employee> loadAll() throws SQLException{
		return exec.executeQuery(cmdLoadAll); 

	}
	@Override
	public Employee loadById(Integer id)throws SQLException{
		return exec.executeQuery(cmdLoadById, id).iterator().next();
	}
	@Override
	public int update(Employee value) throws SQLException {
		return exec.executeUpdate(
				cmdUpdate, 
				value.getTitle(), 
				value.getFirstName(),
				value.getLastName(),
				value.getBirthDate(),
				value.getEmpoyeeId());
	}
	@Override
	public void delete(Employee value) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Employee insert(Employee value) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void close() throws Exception {
		exec.close();		
	}
}
