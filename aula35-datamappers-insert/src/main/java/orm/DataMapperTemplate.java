package orm;

import java.sql.SQLException;
import java.util.Iterator;

public class DataMapperTemplate<K, T extends Entity<K>> implements DataMapper<K, T>{
	final JdbcExecutor exec;
	final JdbcCmd<Iterable<T>> cmdLoadAll;
	final JdbcCmd<Iterable<T>> cmdLoadById;
	final JdbcCmd<T> cmdDelete;
	final JdbcCmd<T> cmdUpdate; 
	final JdbcCmd<K> cmdInsert;
	
	
	public DataMapperTemplate(JdbcExecutor exec,
			JdbcCmd<Iterable<T>> cmdLoadAll, JdbcCmd<Iterable<T>> cmdLoadById,
			JdbcCmd<T> cmdDelete, JdbcCmd<T> cmdUpdate, JdbcCmd<K> cmdInsert) {
		this.exec = exec;
		this.cmdLoadAll = cmdLoadAll;
		this.cmdLoadById = cmdLoadById;
		this.cmdDelete = cmdDelete;
		this.cmdUpdate = cmdUpdate;
		this.cmdInsert = cmdInsert;
	}
	@Override
	public Iterable<T> loadAll() throws SQLException{
		return exec.executeQuery(cmdLoadAll); 

	}
	@Override
	public T loadById(K id)throws SQLException{
		Iterator<T> iter = exec.executeQuery(cmdLoadById, id).iterator();
		return iter.hasNext()? iter.next() : null;
	}
	@Override
	public int update(T value) throws SQLException { // template
		return exec.executeUpdate(
				cmdUpdate, 
				value);
	}
	
	@Override
	public void delete(T value) throws SQLException {
		exec.executeUpdate(cmdDelete, value);
	}
	@Override
	public T insert(T value) throws SQLException {
		K key = exec.executeInsert(cmdInsert, value);
		value.setId(key);
		return value;
	}
	
	@Override
	public void close() throws Exception {
		exec.close();		
	}
}
