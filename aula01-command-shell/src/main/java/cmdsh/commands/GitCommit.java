package cmdsh.commands;

import cmdsh.core.Command;

public class GitCommit implements Command{
	final String name = "git commit";
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void performCommand() {
		System.out.println("Performing git commit");
	}
}
