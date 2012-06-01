package orm;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcCmdUpdate<T> extends JdbcCmdQuery<T>{

	public JdbcCmdUpdate(String sql, JdbcBinder...binders) {
		super(sql, null, binders);
	}

	@Override
	public T loadRows(ResultSet rs) throws SQLException {
		throw new UnsupportedOperationException();
	}

}
