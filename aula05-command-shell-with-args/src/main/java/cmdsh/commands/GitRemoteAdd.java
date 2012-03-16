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
	public void performCommand(String params) {
		params = params.substring(getName().length()+1);
		String [] aParams = params.split(" ");
		int from = 0;
		for (IArgument a : getArgs()) {
			from = a.parse(aParams, from);
		}
	}

}
