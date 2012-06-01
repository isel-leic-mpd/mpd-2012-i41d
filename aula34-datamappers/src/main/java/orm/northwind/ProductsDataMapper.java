package orm.northwind;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.inject.Inject;

import orm.AbstractDataMapper;
import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcCmdQuery;
import orm.JdbcCmdUpdate;
import orm.JdbcConverter;
import orm.JdbcExecutor;

public class ProductsDataMapper extends AbstractDataMapper<Integer, Product>{
	@Inject
	public ProductsDataMapper(JdbcExecutor exec) {
		super(exec, cmdLoadAll, cmdLoadById, cmdDelete, cmdUpdate);
	}

	@Override
	protected Object[] getValuesForUpdate(Product value) {
		return new Object[]{
				value.getProductName(),
				value.getUnitPrice(),
				value.getUnitsInStock(),
				value.getId()
		};
	}
	
	final static String sqlProducts = "SELECT ProductID,ProductName,UnitPrice,UnitsInStock FROM Products"; 
	final static String sqlProductsById = sqlProducts + " WHERE ProductId = ?";
	final static String sqlUpdate = "UPDATE Products SET ProductName= ?, UnitPrice= ?, UnitsInStock = ? WHERE ProductId = ?";
	final static String sqlDelete= "DELETE FROM Products WHERE ProductId = ?";
	
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
			JdbcBinder.IntBinder);
	
	final static JdbcCmd<Product> cmdUpdate = new JdbcCmdUpdate<>(
			sqlUpdate, 
			JdbcBinder.StringBinder,
			JdbcBinder.DoubleBinder,
			JdbcBinder.IntBinder,
			JdbcBinder.IntBinder);
}
