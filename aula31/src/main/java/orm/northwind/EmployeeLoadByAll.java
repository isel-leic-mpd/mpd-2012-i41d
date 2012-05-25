package orm.northwind;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Employee;

import orm.JdbcCmd;

public class EmployeeLoadByAll implements JdbcCmd<Iterable<Employee>>{

	final static String sqlEmployees = "SELECT Title, FirstName, LastName, BirthDate FROM Employees";
	
	@Override
	public String getSqlQuery() {
		return sqlEmployees;
	}

	@Override
	public void bind(PreparedStatement stmt, String... args) throws SQLException {
		
	}

	@Override
	public Iterable<Employee> loadRows(ResultSet rs) throws SQLException {
		List<Employee> res = new LinkedList<Employee>();
		while(rs.next()){
			res.add(new Employee(
					rs.getString(1), 
					rs.getString(2), 
					rs.getString(3), 
					rs.getDate(4)));
		}
		return res;
	}
}
