package orm.northwind;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import com.google.inject.Inject;

import orm.DataMapperTemplate;
import orm.DataMapper;
import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcCmdQuery;
import orm.JdbcCmdUpdate;
import orm.JdbcExecutor;
import orm.JdbcExecutorMultipleConnection;
import sun.org.mozilla.javascript.internal.ast.AstRoot;

public class EmployeesDataMapper extends DataMapperTemplate<Integer, Employee>{

	final static String sqlEmployees = "SELECT EmployeeId, Title, FirstName, LastName, BirthDate FROM Employees";
	final static String sqlUpdateEmployee = "UPDATE Employees SET Title = ?, FirstName = ?, LastName = ?, BirthDate = ? WHERE EmployeeId = ?";
	final static String sqlDeleteEmployee = "DELETE FROM Employees WHERE EmployeeId = ?";

	@Inject
	public EmployeesDataMapper(JdbcExecutor exec) {
		super(exec);
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
			new JdbcBinder<Employee>(){public void bind(PreparedStatement stmt, int idx, Employee arg)throws SQLException {
				stmt.setInt(idx++, arg.getId());
			}});
	final static JdbcCmd<Employee> cmdUpdate = new JdbcCmdUpdate<Employee>(
			sqlUpdateEmployee, 
			new JdbcBinder<Employee>(){public void bind(PreparedStatement stmt, int idx, Employee arg)throws SQLException {
				stmt.setString(idx++, arg.getTitle());
				stmt.setString(idx++, arg.getFirstName());
				stmt.setString(idx++, arg.getLastName());
				stmt.setDate(idx++, new java.sql.Date(arg.getBirthDate().getTime()));
				stmt.setInt(idx++, arg.getId());
			}});

	@Override
	public JdbcCmd<Iterable<Employee>> cmdLoadAll() {
		return cmdLoadAll;
	}
	@Override
	public JdbcCmd<Iterable<Employee>> cmdLoadById() {
		return cmdLoadById;
	}
	@Override
	public JdbcCmd<Employee> cmdDelete() {
		return cmdDelete;
	}
	@Override
	public JdbcCmd<Employee> cmdUpdate() {
		return cmdUpdate;
	}
	@Override
	public JdbcCmd<Integer> cmdInsert() {
		return null;
	}
}
