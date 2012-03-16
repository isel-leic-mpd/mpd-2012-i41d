package cmdsh.commands;

import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;

public class GitAdd extends AbstractCommand{
	public GitAdd() {
		super("git add", new IArgument[]{});
	}

	@Override
	public void performCommand(String params) {
		System.out.println("Performing git add");
	}

}
