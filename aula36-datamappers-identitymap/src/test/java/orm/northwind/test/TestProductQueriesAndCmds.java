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
import com.google.inject.Singleton;
import com.google.inject.util.Modules;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class TestProductQueriesAndCmds {

	Injector inj;

	public TestProductQueriesAndCmds(){
		inj = Guice.createInjector(
				Modules.override(new CfgModule()).with(new AbstractModule(){
					@Override
					protected void configure() {
						bind(JdbcExecutor.class).to(JdbcExecutorSingleConnection.class).in(Singleton.class);
						bind(boolean.class).annotatedWith(DbAutoCommit.class).toInstance(false);
					}					
				}));
	}

	@Test
	public void testLoadAllProducts() throws Exception{
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
	public void testLoadByIdProducts()throws Exception{
		try(ProductsDataMapper empMapper = inj.getInstance(ProductsDataMapper.class)){
			Product e = empMapper.loadById(7);
			Assert.assertEquals("Uncle Bob's Organic Dried Pears", e.getProductName());
			Assert.assertEquals(30.0, e.getUnitPrice());
			Assert.assertEquals(15, e.getUnitsInStock());
			Product newProd = empMapper.loadById(7);
			Assert.assertSame(e, newProd); // => p == newProd
			
					
		}
	}
	
	@Test
	public void testUpdateProduct()throws Exception{
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
			try(ProductsDataMapper newMapper = inj.getInstance(ProductsDataMapper.class)){
				Product newE = newMapper.loadById(7);
				Assert.assertNotSame(e, newE);
				Assert.assertEquals("Cachecol do Campeão Nacional -FCP", newE.getProductName());
				Assert.assertEquals(1000.0, newE.getUnitPrice());
				Assert.assertEquals(2, newE.getUnitsInStock());
			}
		}
	}
	
	@Test
	public void testInsertProduct()throws Exception{
		try(ProductsDataMapper empMapper = inj.getInstance(ProductsDataMapper.class)){
			//
			// Arrange
			//
			Product p = new Product("cachecol do FCP", 1000.0, 7000);
			//
			// Act
			//
			empMapper.insert(p);
			//
			// Assert
			//
			Product newP = empMapper.loadById(p.getId());
			Assert.assertEquals(p.getProductName(), newP.getProductName());
			Assert.assertEquals(p.getUnitPrice(), newP.getUnitPrice());
			Assert.assertEquals(p.getUnitsInStock(), newP.getUnitsInStock());
			//
			// Delete
			// 
			empMapper.delete(p);
			//
			// Assert
			//
			newP = empMapper.loadById(p.getId());
			// Assert.assertEquals(null, newP);
			Assert.assertNull(newP);
		}
	}	
}
