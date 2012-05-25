package orm;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public interface JdbcBinder {
	void bind(PreparedStatement stmt, int idx, String arg) throws SQLException;
	
	public static JdbcBinder IntBinder = new JdbcBinder() {
		@Override
		public void bind(PreparedStatement stmt, int idx, String arg)throws SQLException {
			stmt.setInt(idx, Integer.parseInt(arg));
		}
	};
	
	public static JdbcBinder DateBinder = new JdbcBinder() {
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		@Override
		public void bind(PreparedStatement stmt, int idx, String arg)throws SQLException {
			try {
				stmt.setDate(idx, new java.sql.Date(formatter.parse(arg).getTime()));
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
	}; 

}
