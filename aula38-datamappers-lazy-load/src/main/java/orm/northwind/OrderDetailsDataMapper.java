package orm.northwind;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import orm.DataMapperTemplate;
import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcCmdQuery;
import orm.JdbcConverter;
import orm.JdbcExecutor;

public class OrderDetailsDataMapper extends DataMapperTemplate<Integer, OrderDetails>{
	
	@Inject
	public OrderDetailsDataMapper(JdbcExecutor exec) {
		super(exec);
	}

	final static String sqlLoadAll = "SELECT orderId, ProductId, unitPrice, quantity, discount FROM [Order Details]"; 
	final static String sqlLoadByProductId = sqlLoadAll + " WHERE ProductId = ?"; 
			
	final JdbcConverter<Iterable<OrderDetails>> conv = new JdbcConverter<Iterable<OrderDetails>>() {
		@Override
		public Iterable<OrderDetails> loadRows(ResultSet rs) throws SQLException {
			List<OrderDetails> res = new LinkedList<OrderDetails>();
			
			while(rs.next()){
				int orderId = rs.getInt(1);
				OrderDetails o = getEntity(orderId);
				if(o == null){
					o = new OrderDetails(
							orderId, 
							rs.getInt(2), 
							rs.getDouble(3), 
							rs.getInt(4),
							rs.getDouble(5));
					addEntity(o);
				}
				res.add(o);
			}
			return res;
		}
	}; 
	
	@Override
	public JdbcCmd<Iterable<OrderDetails>> cmdLoadAll() {
		return new JdbcCmdQuery<Iterable<OrderDetails>>(
				sqlLoadAll, 
				conv);
	}
	
	public Iterable<OrderDetails> loadByProductId(int productId) throws SQLException {
		JdbcCmdQuery<Iterable<OrderDetails>> cmd = new JdbcCmdQuery<Iterable<OrderDetails>>(
				sqlLoadByProductId, 
				conv,
				JdbcBinder.IntBinder);
		return exec.executeQuery(cmd, productId);
	}
	
	@Override
	public JdbcCmd<Iterable<OrderDetails>> cmdLoadById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JdbcCmd<OrderDetails> cmdDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JdbcCmd<OrderDetails> cmdUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JdbcCmd<Integer> cmdInsert() {
		// TODO Auto-generated method stub
		return null;
	}

}
