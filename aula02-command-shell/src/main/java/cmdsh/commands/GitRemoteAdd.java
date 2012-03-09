package cmdsh.commands;

import cmdsh.core.ICommand;

public class GitRemoteAdd extends AbstractCommand{
	public GitRemoteAdd() {
		super("git remote add");
	}

	@Override
	public void performCommand() {
		System.out.println("Performing git remote add");		
	}

}
