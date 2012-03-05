package cmdsh.commands;

import cmdsh.core.Command;

public class GitAdd implements Command{
	final String name = "git add";
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void performCommand() {
		System.out.println("Performing git add");
	}

}
