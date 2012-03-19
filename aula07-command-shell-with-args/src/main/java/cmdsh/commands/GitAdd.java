package cmdsh.commands;

import cmdsh.args.ArgFlagOptional;
import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;

public class GitAdd extends AbstractCommand{
	public GitAdd() {
		super("git add", new IArgument[]{
				new ArgFlagOptional("-f"),
				new ArgFlagOptional("-n"),
				new ArgFlagOptional("-v"),
				new ArgFlagOptional("-i")
		});
	}

	@Override
	public void performCommand(String params){
		//
		// Parsing params
		//
		params = params.substring(getName().length());
		String [] aParams = params.split(" ");
		int from = 1;
		for (IArgument a : getArgs()) {
			for (int i = 0; i < aParams.length; i++) {
				int idx = a.parse(aParams, i);
				if(idx != i) break;
			}
		}
		System.out.println("Performing git add");
	}

	@Override
	protected void executeCommand() {
		// TODO Auto-generated method stub		
	}
}
