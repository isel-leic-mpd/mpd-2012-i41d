package cmdsh.parsers;

import cmdsh.core.IArgsParser;
import cmdsh.core.IArgument;

public class ArgsParserOrdered implements IArgsParser{

	@Override
	public void parse(String [] aParams, Iterable<IArgument> args) {
		//
		// Parsing params
		//
		int from = 1;
		for (IArgument a : args) {
			from = a.parse(aParams, from);
		}
	}

}
