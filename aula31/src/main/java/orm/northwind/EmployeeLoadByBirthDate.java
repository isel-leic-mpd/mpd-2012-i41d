package orm.northwind;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import model.Employee;

import orm.JdbcCmd;

public class EmployeeLoadByBirthDate implements JdbcCmd<Iterable<Employee>>{

	final static String sqlEmployees = "SELECT Title, FirstName, LastName, BirthDate " +
			"FROM Employees " +
			"WHERE BirthDate > ?";
	
	@Override
	public String getSqlQuery() {
		return sqlEmployees;
	}

	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	
	@Override
	public void bind(PreparedStatement stmt, String... args) throws SQLException {
		java.util.Date dt;
		try {
			dt = formatter.parse(args[0]);
			stmt.setDate(1, new java.sql.Date(dt.getTime()));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
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
