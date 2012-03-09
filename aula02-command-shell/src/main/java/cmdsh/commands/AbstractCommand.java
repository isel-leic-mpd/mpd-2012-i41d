package cmdsh.commands;

import cmdsh.core.ICommand;

public abstract class AbstractCommand implements ICommand{

	final String name;

	public AbstractCommand(String name) {
		this.name = name;
	}
	@Override
	public String getName() {
		return name;
	}

}