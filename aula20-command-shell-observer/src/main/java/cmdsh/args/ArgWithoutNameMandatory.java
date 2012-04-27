package cmdsh.args;

import cmdsh.core.IArgument;
import cmdsh.core.MissingArgumentException;

public class ArgWithoutNameMandatory implements IArgument<String>{
	private final String name;
	private String value;
	public ArgWithoutNameMandatory(String n) {
		name = n;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public int parse(String[] args, int from) {
		if(from >= args.length) throw new MissingArgumentException();
		value = args[from];
		return ++from;
	}

}
