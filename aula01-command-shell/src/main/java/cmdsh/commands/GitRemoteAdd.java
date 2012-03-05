package cmdsh.commands;

import cmdsh.core.Command;

public class GitRemoteAdd implements Command{
	final String name = "git remote add";
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void performCommand() {
		System.out.println("Performing git remote add");		
	}

}
