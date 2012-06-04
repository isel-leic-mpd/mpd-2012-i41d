package orm;

import java.sql.SQLException;

public interface DataMapper<K, T extends Entity<K>> extends AutoCloseable{
	Iterable<T> loadAll() throws SQLException;
	T loadById(K key)throws SQLException;
	int update(T value)throws SQLException;
	void delete(T value)throws SQLException;
	T insert(T value)throws SQLException;
}
