package cmdsh.core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public abstract class AbstractCommand implements ICommand{

	final String name;
	final List<IArgument> args; 

	public AbstractCommand(String name, IArgument[] args) {
		this.name = name;
		this.args = new LinkedList<IArgument>();
		for (int i = 0; i < args.length; i++) {
			this.args.add(args[i]);
		}
	}
	@Override
	public Iterable<IArgument> getArgs() {
		// return args;
		return Collections.unmodifiableCollection(args);
	}
	
	@Override
	public final String getName() {
		return name;
	}

}