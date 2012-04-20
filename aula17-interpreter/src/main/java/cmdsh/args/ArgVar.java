package cmdsh.args;

import java.util.Iterator;

import cmdsh.core.IArgument;

public class ArgVar implements IArgument<Iterable<String>>{
	final String name; 
	String[] args;
	public ArgVar(String name) {
		this.name = name;
	}
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Iterable<String> getValue() {
		return new Iterable<String>() {
			public Iterator<String> iterator() {
				return new Iterator<String>() {
					int idx = 1;
					public boolean hasNext() {
						return idx < args.length;
					}
					public String next() {
						String cur = args[idx];
						idx++;
						return cur;
					}
					public void remove() {
						throw new UnsupportedOperationException();
					}					
				};
			}
		};
	}
	@Override
	public int parse(String[] args, int from) {
		this.args = args;
		return args.length;
	}
}
