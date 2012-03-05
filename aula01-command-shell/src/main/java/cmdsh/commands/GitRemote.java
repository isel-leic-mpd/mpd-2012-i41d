package cmdsh.commands;

import cmdsh.core.Command;

public class GitRemote implements Command{
	final String name = "git remote";
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void performCommand() {
		System.out.println("Performing git remote");	
	}
}
