package cmdsh.commands;

import cmdsh.core.AbstractCommand;

public class GitRemoteRm extends AbstractCommand{
	
	public GitRemoteRm() {
		super("git remote rm");
	}
	@Override
	public void performCommand(String params) {
		System.out.println("Performing git remote rm");		
	}
}
