package cmdsh.commands;

import cmdsh.args.ArgFlagOptional;
import cmdsh.args.ArgWithoutNameMandatory;
import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;

public class GitRemoteAdd extends AbstractCommand{
	IArgument [] args = new IArgument[4]; 
	
	public GitRemoteAdd() {
		super("git remote add");
		args[0] = new ArgFlagOptional("-f");
		args[1] = new ArgFlagOptional("--tags");
		args[2] = new ArgWithoutNameMandatory("name");
		args[3] = new ArgWithoutNameMandatory("url");
	}

	@Override
	public void performCommand(String params) {
		params = params.substring(getName().length());
		String [] aParams = params.split(" ");
		int from = 0;
		for (IArgument a : args) {
			from = a.parse(aParams, from);
		}
	}

}
