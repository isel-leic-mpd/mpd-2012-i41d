package orm.northwind;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Employee;

import orm.JdbcCmd;

public class EmployeeLoadById implements JdbcCmd<Employee>{

	final static String sqlEmployees = "SELECT Title, FirstName, LastName, BirthDate FROM Employees WHERE EmployeeId = ?";
	
	@Override
	public String getSqlQuery() {
		return sqlEmployees;
	}

	@Override
	public void bind(PreparedStatement stmt, String... args) throws SQLException {
		stmt.setInt(1, Integer.parseInt(args[0]));
	}

	@Override
	public Employee loadRows(ResultSet rs) throws SQLException {
		rs.next();
		return new Employee(
					rs.getString(1), 
					rs.getString(2), 
					rs.getString(3), 
					rs.getDate(4));
	}
}
