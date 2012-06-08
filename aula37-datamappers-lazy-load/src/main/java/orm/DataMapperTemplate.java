package orm;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class DataMapperTemplate<K, T extends Entity<K>> implements DataMapper<K, T>{
	protected final JdbcExecutor exec;
	final Map<K, T> identityMap = new HashMap<K, T>();
	
	
	public abstract JdbcCmd<Iterable<T>> cmdLoadAll();
	public abstract JdbcCmd<Iterable<T>> cmdLoadById();
	public abstract JdbcCmd<T> cmdDelete();
	public abstract JdbcCmd<T> cmdUpdate(); 
	public abstract JdbcCmd<K> cmdInsert();
	
	public DataMapperTemplate(JdbcExecutor exec) {
		this.exec = exec;
	}
	
	public final T getEntity(K key){
		return identityMap.get(key);
	}
	
	public final void addEntity(T entity){
		identityMap.put(entity.getId(), entity);
	}
	
	@Override
	public Iterable<T> loadAll() throws SQLException{
		return exec.executeQuery(cmdLoadAll()); 

	}
	@Override
	public T loadById(K id)throws SQLException{
		T e = getEntity(id);
		if(e != null) return e;
		Iterator<T> iter = exec.executeQuery(cmdLoadById(), id).iterator();
		return iter.hasNext()? iter.next() : null;
	}
	@Override
	public int update(T value) throws SQLException { // template
		return exec.executeUpdate(
				cmdUpdate(), 
				value);
	}
	
	@Override
	public void delete(T value) throws SQLException {
		exec.executeUpdate(cmdDelete(), value);
		identityMap.remove(value.getId());
	}
	@Override
	public T insert(T value) throws SQLException {
		K key = exec.executeInsert(cmdInsert(), value);
		value.setId(key);
		identityMap.put(key, value);
		return value;
	}
	
	@Override
	public void close() throws Exception {
		exec.close();		
	}
}
