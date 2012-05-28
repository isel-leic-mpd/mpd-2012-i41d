package app;

import java.sql.SQLException;

import java.util.Scanner;

import orm.JdbcExecutorMultipleConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;

import static java.lang.System.in;
import static java.lang.System.out;

public class Program {

	public static void main(String[] args) throws SQLException {		
		Injector inj = Guice.createInjector(new CfgModule());
		JdbcExecutorMultipleConnection db = inj.getInstance(JdbcExecutorMultipleConnection.class);
}
}
