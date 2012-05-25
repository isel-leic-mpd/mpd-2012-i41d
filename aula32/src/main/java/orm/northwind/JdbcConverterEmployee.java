package orm.northwind;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import orm.JdbcConverter;

public class JdbcConverterEmployee implements JdbcConverter<Iterable<Employee>>{
	
	public static JdbcConverterEmployee SIGLETON = new JdbcConverterEmployee();
	
	private JdbcConverterEmployee(){}
	
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