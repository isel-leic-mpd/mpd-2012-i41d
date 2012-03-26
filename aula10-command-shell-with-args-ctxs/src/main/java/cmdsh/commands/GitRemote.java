package cmdsh.commands;

import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;
import cmdsh.core.IContext;
import cmdsh.parsers.ArgsParserOrdered;

public class GitRemote extends AbstractCommand{
	public GitRemote() {
		super("git remote", new ArgsParserOrdered(), new IArgument[]{});
	}

	@Override
	public void executeCommand(){
		System.out.println("Performing git remote");	
	}
}
