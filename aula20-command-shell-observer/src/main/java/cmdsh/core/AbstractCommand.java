package cmdsh.core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public abstract class AbstractCommand implements IContext{

	final String name;
	/**
	 * @uml.associationEnd  aggregation="shared" inverse="cmdsh.core.IArgument" multiplicity="(0 -1)" 
	 */
	final List<IArgument> args; 
	final IArgsParser parser;
	
	public AbstractCommand(String name, IArgsParser parser, IArgument[] args) {
		this.name = name;
		this.parser = parser;
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
	
	@Override
	public void performCommand(String params) {
		params = params.substring(getName().length());
		String [] aParams = params.split(" ");
		parser.parse(aParams, args);
		executeCommand();
	}
	protected abstract void executeCommand();
	
	public void add(IContext c, String[] name, int from){
		throw new UnsupportedOperationException();
	}
	public IContext get(String[] aux, int from){
		return this;
	}
}