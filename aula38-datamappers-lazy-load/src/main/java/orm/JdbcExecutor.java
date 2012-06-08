package orm;

import java.sql.SQLException;

public interface JdbcExecutor extends AutoCloseable{
	<T> T executeQuery(JdbcCmd<T> cmd, Object...args) throws SQLException;
	<T> int executeUpdate(JdbcCmd<T> cmd, T arg) throws SQLException;
	<K, T extends Entity<K>> K executeInsert(JdbcCmd<K> cmd, T arg) throws SQLException;
}
