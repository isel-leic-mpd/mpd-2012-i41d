package cmdsh.commands;

import cmdsh.core.Command;

public class GitRemoteRm implements Command{
	final String name = "git remote rm";
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void performCommand() {
		System.out.println("Performing git remote rm");		
	}
}
