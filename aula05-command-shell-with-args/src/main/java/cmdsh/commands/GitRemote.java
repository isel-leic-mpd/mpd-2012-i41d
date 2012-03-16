package cmdsh.commands;

import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;

public class GitRemote extends AbstractCommand{
	public GitRemote() {
		super("git remote", new IArgument[]{});
	}

	@Override
	public void performCommand(String params) {
		System.out.println("Performing git remote");	
	}
}
