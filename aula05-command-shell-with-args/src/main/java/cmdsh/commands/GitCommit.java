package cmdsh.commands;

import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;

public class GitCommit extends AbstractCommand{
	public GitCommit() {
		super("git commit", new IArgument[]{});
	}
	@Override
	public void performCommand(String params) {
		System.out.println("Performing git commit");
	}
}
