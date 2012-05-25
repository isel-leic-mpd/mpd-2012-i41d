package app;

import java.sql.SQLException;
import java.util.Scanner;

import orm.JdbcExecutor;
import orm.northwind.EmployeeLoadByBirthDate;
import orm.northwind.EmployeeLoadById;

import com.google.inject.Guice;
import com.google.inject.Injector;

import static java.lang.System.in;
import static java.lang.System.out;

public class Program {

	public static void main(String[] args) throws SQLException {		
		Injector inj = Guice.createInjector(new CfgModule());
		JdbcExecutor db = inj.getInstance(JdbcExecutor.class);
		EmployeeLoadById loadById = new EmployeeLoadById();
		EmployeeLoadByBirthDate loadByDate = new EmployeeLoadByBirthDate();
		
		//
		// run command shell
		//
		Scanner cin = new Scanner(in);
		out.println("****** Commmand shell application *****");
		while(true){
			out.print("> Insert a date: ");
			out.flush();
			String inLine = cin.nextLine();
			out.println(db.executeCmd(loadByDate, inLine));
		}
	}
}
