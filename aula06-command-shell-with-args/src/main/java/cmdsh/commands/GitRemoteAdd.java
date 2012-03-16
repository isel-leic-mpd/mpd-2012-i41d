package cmdsh.commands;

import cmdsh.args.ArgFlagOptional;
import cmdsh.args.ArgWithoutNameMandatory;
import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;

public class GitRemoteAdd extends AbstractCommand{

	public GitRemoteAdd() {
		super("git remote add", new IArgument[]{
				new ArgFlagOptional("-f"), 
				new ArgFlagOptional("--tags"), 
				new ArgWithoutNameMandatory("name"), 
				new ArgWithoutNameMandatory("url")});
	}

	@Override
	public void executeCommand(){
		
		//
		// Executing command
		//
		System.out.println("Performing git remote add...");
	}
}
