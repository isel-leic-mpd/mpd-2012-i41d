package app;

import javax.sql.DataSource;

import orm.DbAutoCommit;
import orm.DbPasswd;
import orm.DbUser;
import orm.JdbcExecutor;
import orm.JdbcExecutorMultipleConnection;
import orm.JdbcExecutorSingleConnection;

import com.google.inject.AbstractModule;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class CfgModule extends AbstractModule{
	@Override
	protected void configure() {
		/*
		SQLServerDataSource ds = new SQLServerDataSource();
		ds.setUser("myAppUser");
		ds.setPassword("fcp");
		bind(DataSource.class).toInstance(ds);
		*/
		
		bind(JdbcExecutor.class).to(JdbcExecutorMultipleConnection.class);
		bind(DataSource.class).to(SQLServerDataSource.class);
		bind(String.class).annotatedWith(DbUser.class).toInstance("myAppUser");
		bind(String.class).annotatedWith(DbPasswd.class).toInstance("fcp");
		bind(boolean.class).annotatedWith(DbAutoCommit.class).toInstance(true);
		
	}
}
