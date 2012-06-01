package orm.northwind;

import java.sql.SQLException;
import java.util.Iterator;

import com.google.inject.Inject;

import orm.AbstractDataMapper;
import orm.DataMapper;
import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcCmdQuery;
import orm.JdbcCmdUpdate;
import orm.JdbcExecutor;
import orm.JdbcExecutorMultipleConnection;
import sun.org.mozilla.javascript.internal.ast.AstRoot;

public class EmployeesDataMapper extends AbstractDataMapper<Integer, Employee>{

	final static String sqlEmployees = "SELECT EmployeeId, Title, FirstName, LastName, BirthDate FROM Employees";
	final static String sqlUpdateEmployee = "UPDATE Employees SET Title = ?, FirstName = ?, LastName = ?, BirthDate = ? WHERE EmployeeId = ?";
	final static String sqlDeleteEmployee = "DELETE FROM Employees WHERE EmployeeId = ?";

	@Inject
	public EmployeesDataMapper(JdbcExecutor exec) {
		super(exec, cmdLoadAll, cmdLoadById, cmdDelete, cmdUpdate);
	}
	final static JdbcCmd<Iterable<Employee>> cmdLoadAll = new JdbcCmdQuery<Iterable<Employee>>(
			sqlEmployees, 
			JdbcConverterEmployee.SIGLETON);

	final static JdbcCmd<Iterable<Employee>> cmdLoadById = new JdbcCmdQuery<Iterable<Employee>>(
			sqlEmployees + " WHERE EmployeeId = ?", 
			JdbcConverterEmployee.SIGLETON, 
			JdbcBinder.IntBinder
			);
	final static JdbcCmd<Employee> cmdDelete= new JdbcCmdUpdate<Employee>(
			sqlDeleteEmployee,
			JdbcBinder.IntBinder
			);
	final static JdbcCmd<Employee> cmdUpdate = new JdbcCmdUpdate<Employee>(
			sqlUpdateEmployee, 
			JdbcBinder.StringBinder,
			JdbcBinder.StringBinder,
			JdbcBinder.StringBinder,
			JdbcBinder.DateBinder,
			JdbcBinder.IntBinder
			);


	@Override
	protected Object[] getValuesForUpdate(Employee value) {
		return new Object[]{
				value.getTitle(), 
				value.getFirstName(), 
				value.getLastName(), 
				value.getBirthDate(),
				value.getId()};
	} 
}
