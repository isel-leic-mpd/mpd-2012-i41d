package orm.northwind.test;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;


import org.junit.Test;

import orm.DbAutoCommit;
import orm.JdbcExecutor;
import orm.JdbcExecutorSingleConnection;
import orm.northwind.Product;
import orm.northwind.ProductsDataMapper;
import app.CfgModule;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class TestProductQueriesAndCmds {

	Injector inj;

	public TestProductQueriesAndCmds(){
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
	public void testLoadAllEmployees() throws Exception{
		try(ProductsDataMapper empMapper = inj.getInstance(ProductsDataMapper.class)){
			Iterable<Product> res = empMapper.loadAll();
			int count = 0;
			for (Product p : res) {
				count++;
			}
			Assert.assertEquals(count, 77);
		}
	}
	@Test
	public void testLoadByIdEmployees()throws Exception{
		try(ProductsDataMapper empMapper = inj.getInstance(ProductsDataMapper.class)){
			Product e = empMapper.loadById(7);
			Assert.assertEquals("Uncle Bob's Organic Dried Pears", e.getProductName());
			Assert.assertEquals(30.0, e.getUnitPrice());
			Assert.assertEquals(15, e.getUnitsInStock());
			
		}
	}
	@Test
	public void testUpdateEmployee()throws Exception{
		try(ProductsDataMapper empMapper = inj.getInstance(ProductsDataMapper.class)){
			Product e = empMapper.loadById(7);
			Assert.assertEquals("Uncle Bob's Organic Dried Pears", e.getProductName());
			Assert.assertEquals(30.0, e.getUnitPrice());
			Assert.assertEquals(15, e.getUnitsInStock());
			//
			// Arrange
			//
			e.setProductName("Cachecol do Campeão Nacional -FCP");
			e.setUnitPrice(1000);
			e.setUnitsInStock(2);
			//
			// Act
			//		
			empMapper.update(e);
			//
			// Assert
			//
			e = empMapper.loadById(7);
			Assert.assertEquals("Cachecol do Campeão Nacional -FCP", e.getProductName());
			Assert.assertEquals(1000.0, e.getUnitPrice());
			Assert.assertEquals(2, e.getUnitsInStock());
		}
	}
}
