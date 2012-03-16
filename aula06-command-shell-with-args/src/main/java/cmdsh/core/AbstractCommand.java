package cmdsh.core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public abstract class AbstractCommand implements ICommand{

	final String name;
	/**
	 * @uml.associationEnd  aggregation="shared" inverse="cmdsh.core.IArgument" multiplicity="(0 -1)" 
	 */
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
	private final void parse(String params) {
		//
		// Parsing params
		//
		params = params.substring(getName().length());
		String [] aParams = params.split(" ");
		int from = 1;
		for (IArgument a : getArgs()) {
			from = a.parse(aParams, from);
		}
	}
	@Override
	public final void performCommand(String params) {
		parse(params);
		executeCommand();
	}
	protected abstract void executeCommand();
}