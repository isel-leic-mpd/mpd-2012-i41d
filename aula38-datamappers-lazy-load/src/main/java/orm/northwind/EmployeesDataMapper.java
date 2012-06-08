package orm.northwind;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.inject.Inject;

import orm.DataMapperTemplate;
import orm.DataMapper;
import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcCmdQuery;
import orm.JdbcCmdUpdate;
import orm.JdbcConverter;
import orm.JdbcExecutor;
import orm.JdbcExecutorMultipleConnection;
import orm.ValueHolder;
import sun.org.mozilla.javascript.internal.ast.AstRoot;

public class EmployeesDataMapper extends DataMapperTemplate<Integer, Employee>{

	final static String sqlEmployees = "SELECT EmployeeId, Title, FirstName, LastName, BirthDate, reportsTo  FROM Employees";
	final static String sqlUpdateEmployee = "UPDATE Employees SET Title = ?, FirstName = ?, LastName = ?, BirthDate = ? WHERE EmployeeId = ?";
	final static String sqlDeleteEmployee = "DELETE FROM Employees WHERE EmployeeId = ?";

	JdbcConverter<Iterable<Employee>> conv = new JdbcConverter<Iterable<Employee>>() {
		public Iterable<Employee> loadRows(ResultSet rs) throws SQLException {
			List<Employee> res = new LinkedList<Employee>();
			while(rs.next()){
				final int id = rs.getInt(1);
				final int reportsToId = rs.getInt(6);
				res.add(new Employee(
						id,
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getDate(5),
						new Iterable<Employee>() {
							@Override
							public Iterator<Employee> iterator() {
								try {
									return EmployeesDataMapper.this.loadEmployeeByReportsTo(id).iterator();
								} catch (SQLException e) {
									throw new RuntimeException(e);								
								}
							}
						},
						new ValueHolder<Employee>() {
							Employee wrapped;
							@Override
							public Employee get() {
								if(wrapped == null)
									try {
										wrapped = EmployeesDataMapper.this.loadById(reportsToId);
									} catch (SQLException e) {
										throw new RuntimeException(e);
									}
								return  wrapped;

							}
						}
						));
			}
			return res;		
		}
	};

	@Inject
	public EmployeesDataMapper(JdbcExecutor exec) {
		super(exec);
	}
	final JdbcCmd<Iterable<Employee>> cmdLoadAll = new JdbcCmdQuery<Iterable<Employee>>(
			sqlEmployees, 
			conv);

	final JdbcCmd<Iterable<Employee>> cmdLoadById = new JdbcCmdQuery<Iterable<Employee>>(
			sqlEmployees + " WHERE EmployeeId = ?", 
			conv, 
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

	public Iterable<Employee> loadEmployeeByReportsTo(int managerId) throws SQLException{
		JdbcCmd<Iterable<Employee>> cmd = new JdbcCmdQuery<Iterable<Employee>>(
				sqlEmployees + " WHERE reportsTo = ?",
				conv,
				JdbcBinder.IntBinder);
		return exec.executeQuery(cmd, managerId);
	}

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
