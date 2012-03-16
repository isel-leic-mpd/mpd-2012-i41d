package cmdsh.commands;

import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;

public class GitRemoteRm extends AbstractCommand{
	
	public GitRemoteRm() {
		super("git remote rm", new IArgument[]{});
	}
	@Override
	public void performCommand(String params) {
		System.out.println("Performing git remote rm");		
	}
}
