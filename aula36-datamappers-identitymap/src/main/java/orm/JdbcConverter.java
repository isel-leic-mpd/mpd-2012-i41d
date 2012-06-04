package orm;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface JdbcConverter<T>{
	T loadRows(ResultSet rs) throws SQLException;
}
