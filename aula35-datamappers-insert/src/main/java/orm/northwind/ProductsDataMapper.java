package orm.northwind;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.inject.Inject;

import orm.DataMapperTemplate;
import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcCmdQuery;
import orm.JdbcCmdUpdate;
import orm.JdbcConverter;
import orm.JdbcExecutor;

public class ProductsDataMapper extends DataMapperTemplate<Integer, Product>{
	@Inject
	public ProductsDataMapper(JdbcExecutor exec) {
		super(exec, cmdLoadAll, cmdLoadById, cmdDelete, cmdUpdate, cmdInsert);
	}
	
	final static String sqlProducts = "SELECT ProductID,ProductName,UnitPrice,UnitsInStock FROM Products"; 
	final static String sqlProductsById = sqlProducts + " WHERE ProductId = ?";
	final static String sqlUpdate = "UPDATE Products SET ProductName= ?, UnitPrice= ?, UnitsInStock = ? WHERE ProductId = ?";
	final static String sqlDelete = "DELETE FROM Products WHERE ProductId = ?";
	final static String sqlInsert = "INSERT INTO Products (ProductName, UnitPrice, UnitsInStock) VALUES ( ?, ?, ?)";
	
	final static JdbcConverter<Iterable<Product>> conv = new JdbcConverter<Iterable<Product>>() {
		@Override
		public Iterable<Product> loadRows(ResultSet rs) throws SQLException {
			LinkedList<Product> prods = new LinkedList<Product>();
			while(rs.next()){
				prods.add(new Product(
						rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4)));
			}
			return prods;
		}
	};
	
	final static JdbcCmd<Iterable<Product>> cmdLoadAll = new JdbcCmdQuery<>(
			sqlProducts, 
			conv);
	
	final static JdbcCmd<Iterable<Product>> cmdLoadById = new JdbcCmdQuery<>(
			sqlProductsById, 
			conv, 
			JdbcBinder.IntBinder);
	
	final static JdbcCmd<Product> cmdDelete = new JdbcCmdUpdate<>(
			sqlDelete, 
			new JdbcBinder<Product>(){public void bind(PreparedStatement stmt, int idx, Product arg) throws SQLException {
				stmt.setInt(idx++, arg.getId());
			}});
	
	final static JdbcCmd<Product> cmdUpdate = new JdbcCmdUpdate<>(
			sqlUpdate, 
			new JdbcBinder<Product>(){public void bind(PreparedStatement stmt, int idx, Product arg) throws SQLException {
					stmt.setString(idx++, arg.getProductName());
					stmt.setDouble(idx++, arg.getUnitPrice());
					stmt.setInt(idx++, arg.getUnitsInStock());
					stmt.setInt(idx++, arg.getId());
				}});

	final static JdbcConverter<Integer> convKey = new JdbcConverter<Integer>(){
		@Override
		public Integer loadRows(ResultSet rs) throws SQLException {
			rs.next();
			return rs.getInt(1);
		}
	};
	
	final static JdbcCmd<Integer> cmdInsert= new JdbcCmdQuery<Integer>(
			sqlInsert,
			convKey,
			new JdbcBinder<Product>(){public void bind(PreparedStatement stmt, int idx, Product arg) throws SQLException {
				stmt.setString(idx++, arg.getProductName());
				stmt.setDouble(idx++, arg.getUnitPrice());
				stmt.setInt(idx++, arg.getUnitsInStock());
			}});
}
