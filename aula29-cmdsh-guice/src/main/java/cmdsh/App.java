package cmdsh;

import guiutil.Billboard;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import com.google.inject.Guice;
import com.google.inject.Injector;

import cmdsh.commands.GitAdd;
import cmdsh.commands.GitCommit;
import cmdsh.commands.GitRemoteAdd;
import cmdsh.commands.GitRemoteRm;
import cmdsh.commands.IoUnzip;
import cmdsh.commands.IoZip;
import cmdsh.core.AbstractCommand;
import cmdsh.core.CmdPrintOnBillboard;
import cmdsh.core.CmdPrintTime;
import cmdsh.core.Context;
import cmdsh.core.IContext;
import cmdsh.core.NoSuchCommandException;
import static java.lang.System.in; 
import static java.lang.System.out;

/**
 * @uml.dependency   supplier="cmdsh.commands.GitAdd"
 * @uml.dependency   supplier="cmdsh.commands.GitCommit"
 * @uml.dependency   supplier="cmdsh.commands.GitRemoteAdd"
 */
public class App {
	public static void main(String[] args) throws Exception{
		//
		// Initialize and configure container
		//
		Injector inj = Guice.createInjector(new CmdshCfgModule());
		Context cnt = inj.getInstance(Context.class);
		//
		// run command shell
		//
		Scanner cin = new Scanner(in);
		out.println("****** Commmand shell application *****");
		while(true){
			out.print("> ");
			out.flush();
			String inLine = cin.nextLine();
			IContext c = cnt.getContext(inLine );
			c.performCommand(inLine);
		}
	}
}
