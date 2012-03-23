package cmdsh.commands;

import cmdsh.args.ArgFlagOptional;
import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;
import cmdsh.parsers.ArgsParserOrdered;
import cmdsh.parsers.ArgsParserUnordered;

public class GitAdd extends AbstractCommand{
	public GitAdd() {
		super("git add", new ArgsParserUnordered(), new IArgument[]{
				new ArgFlagOptional("-f"),
				new ArgFlagOptional("-n"),
				new ArgFlagOptional("-v"),
				new ArgFlagOptional("-i")
		});
	}
	@Override
	protected void executeCommand() {
		System.out.println("Performing git add");		
	}
}
