package cmdsh;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import cmdsh.commands.GitAdd;
import cmdsh.commands.GitCommit;
import cmdsh.commands.GitRemoteAdd;
import cmdsh.commands.GitRemoteRm;
import cmdsh.commands.IoUnzip;
import cmdsh.commands.IoZip;
import cmdsh.core.AbstractCommand;
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
		Map<String, Method> inconsistentCmds = new HashMap<String, Method>();
		Collection<AbstractCommand> cmds = new LinkedList<AbstractCommand>();
		File dir = new File("cmdsh\\commands");
		for (String fileName : dir.list()) {
			int idx;
			if((idx = fileName.indexOf(".class")) >= 0){
				String className = fileName.substring(0, idx);
				className = "cmdsh.commands." + className;
				Class c = Class.forName(className);
				if(AbstractCommand.class.isAssignableFrom(c)){
					Object o = c.newInstance();
					cmds.add((AbstractCommand)o);
				}else{
					Method m = c.getMethod("performCommand");
					if((m != null) && ((m.getModifiers() & Modifier.STATIC) != 0)){
						inconsistentCmds.put(c.getSimpleName(), m);
					}
				}
			}
		}
		Context cnt = new Context("CmdSh",cmds.toArray(new IContext[cmds.size()]));
		//
		// run command shell
		//
		Scanner cin = new Scanner(in);
		out.println("****** Commmand shell application *****");
		while(true){
			out.print("> ");
			out.flush();
			String inLine = cin.nextLine();
			try{
				IContext c = cnt.getContext(inLine );
				c.performCommand(inLine);
			}catch(NoSuchCommandException e){
				Method m = inconsistentCmds.get(inLine);
				m.invoke(null);
			}
		}
	}
}
