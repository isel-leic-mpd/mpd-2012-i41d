package orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import orm.northwind.Employee;

import com.google.inject.Inject;


public class JdbcExecutorMultipleConnection implements JdbcExecutor{

	final DataSource ds;
	final String user;
	String password;


	@Inject
	public JdbcExecutorMultipleConnection(DataSource ds, @DbUser String user, @DbPasswd String password) {
		this.ds = ds;
		this.user = user;
		this.password = password;
	}

	public <T> T executeQuery(JdbcCmd<T> cmd, Object...args) throws SQLException{
		List<Employee> res = new LinkedList<Employee>(); 
		try(
			Connection connect =  ds.getConnection(user, password);
			PreparedStatement stmt = connect.prepareStatement(cmd.getSqlQuery());
		){
			cmd.bind(stmt, args);
			ResultSet rs = stmt.executeQuery();
			return cmd.loadRows(rs);
		}
	}
	public <T> int executeUpdate(JdbcCmd<T> cmd, T arg) throws SQLException{
		List<Employee> res = new LinkedList<Employee>(); 
		try(
			Connection connect =  ds.getConnection(user, password);
			PreparedStatement stmt = connect.prepareStatement(cmd.getSqlQuery());
		){
			cmd.bind(stmt, arg);
			return stmt.executeUpdate();
		}
	}
	
	@Override
	public <K, T extends Entity<K>> K executeInsert(JdbcCmd<K> cmd, T arg) throws SQLException {
		try(
			Connection connect =  ds.getConnection(user, password);
			PreparedStatement stmt = connect.prepareStatement(cmd.getSqlQuery(), Statement.RETURN_GENERATED_KEYS);
		){
			cmd.bind(stmt, arg);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			return cmd.loadRows(rs);
		}		
	}	

	@Override
	public void close() throws Exception {
	}	
}
