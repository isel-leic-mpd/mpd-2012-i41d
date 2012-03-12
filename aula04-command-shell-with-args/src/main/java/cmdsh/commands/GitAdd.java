package cmdsh.commands;

import cmdsh.core.AbstractCommand;
import cmdsh.core.ICommand;

public class GitAdd extends AbstractCommand{
	public GitAdd() {
		super("git add");
	}

	@Override
	public void performCommand(String params) {
		System.out.println("Performing git add");
	}

}
