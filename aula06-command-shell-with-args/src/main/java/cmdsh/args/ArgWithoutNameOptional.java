package cmdsh.args;

import cmdsh.core.IArgument;

public class ArgWithoutNameOptional implements IArgument {
	private final String name;
	private String value;
	public ArgWithoutNameOptional(String n) {
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
		if(from >= args.length) return from;
		value = args[from];
		return ++from;
	}

}
