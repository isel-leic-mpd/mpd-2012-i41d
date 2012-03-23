package cmdsh.args;

import cmdsh.core.IArgument;

public class ArgFlagOptional implements IArgument<Boolean>{
	private final String name;
	private Boolean value;
	
	public ArgFlagOptional(String name) {
		this.name = name;
	}
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Boolean getValue() {
		return value;
	}
	@Override
	public int parse(String[] args, int from) {
		this.value = false;
		if(from >= args.length) return from;
		
		if(args[from].equals(name)){
			value = true;
			return ++from; 
		}else{
			return from;
		}
	}

}
