package cmdsh.args;

import cmdsh.core.IArgument;
import cmdsh.core.MissingArgumentException;

public class ArgWithNameOptional implements IArgument<String>{
	private final String name;
	private String value;
	public ArgWithNameOptional(String n) {
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
		if(args[from].equals(name)){
			value = args[++from];
			return ++from;
		}else{
			return from;
		}
	}

}
