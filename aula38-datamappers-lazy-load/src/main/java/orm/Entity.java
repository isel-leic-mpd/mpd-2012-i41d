package orm;

public interface Entity<K>{
	K getId();
	void setId(K key);
}
