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
import com.google.inject.Singleton;

public class JdbcExecutorSingleConnection implements JdbcExecutor, AutoCloseable{

	final DataSource ds;
	final String user;
	String password;
	final boolean autocommit;
	Connection connect;
	
	@Inject
	public JdbcExecutorSingleConnection(DataSource ds, 
			@DbUser String user, 
			@DbPasswd String password, 
			@DbAutoCommit boolean autocommit) {
		this.ds = ds;
		this.user = user;
		this.password = password;
		this.autocommit = autocommit;
	}
	
	private void initConnection() throws SQLException{
		if(connect == null){
			connect =  ds.getConnection(user, password);
			connect.setAutoCommit(autocommit);
		}
	}
	
	public <T> T executeQuery(JdbcCmd<T> cmd, Object...args) throws SQLException{
		List<Employee> res = new LinkedList<Employee>();
		initConnection();
		try(
			PreparedStatement stmt = connect.prepareStatement(cmd.getSqlQuery());
		){
			cmd.bind(stmt, args);
			ResultSet rs = stmt.executeQuery();
			return cmd.loadRows(rs);
		}
	}
	public <T> int executeUpdate(JdbcCmd<T> cmd, T args) throws SQLException{
		List<Employee> res = new LinkedList<Employee>();
		initConnection();
		try(
			PreparedStatement stmt = connect.prepareStatement(cmd.getSqlQuery());
		){
			cmd.bind(stmt, args);
			return stmt.executeUpdate();
		}
	}
	@Override
	public <K, T extends Entity<K>> K executeInsert(JdbcCmd<K> cmd, T args) throws SQLException {
		initConnection();
		try(
			PreparedStatement stmt = connect.prepareStatement(cmd.getSqlQuery(), Statement.RETURN_GENERATED_KEYS);
		){
			cmd.bind(stmt, args);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			return cmd.loadRows(rs);
		}		
	}	

	@Override
	public void close() throws Exception {
		if(connect != null){
			if(autocommit == false)
				connect.rollback();
			connect.close();
			connect = null;
		}		
	}

}
