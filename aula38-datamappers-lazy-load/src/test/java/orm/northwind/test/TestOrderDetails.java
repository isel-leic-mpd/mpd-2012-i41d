package orm.northwind.test;

import junit.framework.Assert;

import org.junit.Test;

import orm.DbAutoCommit;
import orm.JdbcExecutor;
import orm.JdbcExecutorSingleConnection;
import orm.northwind.OrderDetails;
import orm.northwind.OrderDetailsDataMapper;
import app.CfgModule;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;

public class TestOrderDetails {
	
	Injector inj;

	public TestOrderDetails (){
		inj = Guice.createInjector(
				Modules.override(new CfgModule()).with(new AbstractModule(){
					@Override
					protected void configure() {
						bind(JdbcExecutor.class).to(JdbcExecutorSingleConnection.class);
						bind(boolean.class).annotatedWith(DbAutoCommit.class).toInstance(false);
					}					
				}));
	}

	@Test
	public void test_get_orders_by_productId() throws Exception{
		try(OrderDetailsDataMapper mapper = inj.getInstance(OrderDetailsDataMapper.class)){
			Iterable<OrderDetails> orders = mapper.loadByProductId(9);
			int[] expectedOrdersIds = {10420, 10515, 10687, 10693, 10848};
			int idx = 0;
			for (OrderDetails o: orders) {
				Assert.assertEquals(expectedOrdersIds[idx++], o.orderId);
			}			
		}
	}
}
