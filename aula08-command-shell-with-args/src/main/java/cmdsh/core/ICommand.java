package cmdsh.core;

import java.util.List;

public interface ICommand {
	String getName();
	void performCommand(String params);
	Iterable<IArgument> getArgs();
}
