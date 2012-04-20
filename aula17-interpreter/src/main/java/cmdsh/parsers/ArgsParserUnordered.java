package cmdsh.parsers;

import cmdsh.core.IArgsParser;
import cmdsh.core.IArgument;

public class ArgsParserUnordered implements IArgsParser{

	@Override
	public void parse(String [] aParams, Iterable<IArgument> args) {
		//
		// Parsing params
		//
		int from = 1;
		for (IArgument a : args) {
			for (int i = 0; i < aParams.length; i++) {
				int idx = a.parse(aParams, i);
				if(idx != i) break;
			}
		}

	}

}
