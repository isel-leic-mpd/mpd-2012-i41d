package app;

import java.sql.SQLException;

import java.util.Scanner;

import orm.JdbcExecutor;

import com.google.inject.Guice;
import com.google.inject.Injector;

import static java.lang.System.in;
import static java.lang.System.out;

public class Program {

	public static void main(String[] args) throws SQLException {		
		Injector inj = Guice.createInjector(new CfgModule());
		JdbcExecutor db = inj.getInstance(JdbcExecutor.class);
}
}
