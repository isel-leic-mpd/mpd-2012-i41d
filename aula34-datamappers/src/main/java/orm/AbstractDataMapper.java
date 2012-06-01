package orm;

import java.sql.SQLException;
import java.util.Iterator;

public abstract class AbstractDataMapper<K, T extends Entity<K>> implements DataMapper<K, T>{
	final JdbcExecutor exec;
	final JdbcCmd<Iterable<T>> cmdLoadAll;
	final JdbcCmd<Iterable<T>> cmdLoadById;
	final JdbcCmd<T> cmdDelete;
	final JdbcCmd<T> cmdUpdate; 

	
	
	public AbstractDataMapper(JdbcExecutor exec,
			JdbcCmd<Iterable<T>> cmdLoadAll, JdbcCmd<Iterable<T>> cmdLoadById,
			JdbcCmd<T> cmdDelete, JdbcCmd<T> cmdUpdate) {
		this.exec = exec;
		this.cmdLoadAll = cmdLoadAll;
		this.cmdLoadById = cmdLoadById;
		this.cmdDelete = cmdDelete;
		this.cmdUpdate = cmdUpdate;
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
				getValuesForUpdate(value));
	}
	
	protected abstract Object [] getValuesForUpdate(T value); // Hook method
	
	@Override
	public void delete(T value) throws SQLException {
		exec.executeUpdate(cmdDelete, value.getId());
	}
	@Override
	public T insert(T value) throws SQLException {
		return null;
	}
	@Override
	public void close() throws Exception {
		exec.close();		
	}
}
