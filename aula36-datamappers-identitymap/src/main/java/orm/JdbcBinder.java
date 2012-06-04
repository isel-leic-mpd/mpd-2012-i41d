package orm;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface JdbcBinder<T>{
	void bind(PreparedStatement stmt, int idx, T arg) throws SQLException;
	
	public static JdbcBinder<Integer> IntBinder = new JdbcBinder<Integer>() {
		@Override
		public void bind(PreparedStatement stmt, int idx, Integer arg)throws SQLException {
			stmt.setInt(idx, arg);
		}
	};
	
	public static JdbcBinder<Date> DateBinder = new JdbcBinder<Date>() {
		@Override
		public void bind(PreparedStatement stmt, int idx, Date arg)throws SQLException {
			stmt.setDate(idx, new java.sql.Date(arg.getTime()));
		}
	};

	public static JdbcBinder<String> StringBinder = new JdbcBinder<String>() {
		@Override
		public void bind(PreparedStatement stmt, int idx, String arg)throws SQLException {
			stmt.setString(idx, arg);
		}
	};

	public static JdbcBinder<Double> DoubleBinder = new JdbcBinder<Double>() {
		@Override
		public void bind(PreparedStatement stmt, int idx, Double arg) throws SQLException {
			stmt.setDouble(idx, arg);
		}
	};; 

}
