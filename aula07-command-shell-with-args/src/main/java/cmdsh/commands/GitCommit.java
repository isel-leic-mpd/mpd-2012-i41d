package cmdsh.commands;

import cmdsh.args.ArgWithNameOptional;
import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;

public class GitCommit extends AbstractCommand{
	public GitCommit() {
		super("git commit", new IArgument[]{new ArgWithNameOptional("-m")});
	}
	@Override
	public void executeCommand(){
		System.out.println("Performing git commit");
	}
}
