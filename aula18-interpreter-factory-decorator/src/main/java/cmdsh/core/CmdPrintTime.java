package cmdsh.core;

import cmdsh.parsers.ArgsParserOrdered;

public class CmdPrintTime extends AbstractCommand{
	private final AbstractCommand cmd;

	public CmdPrintTime(AbstractCommand cmd) {
		super(cmd.name, new ArgsParserOrdered(), new IArgument[]{});
		this.cmd = cmd;
	}
	@Override
	public void performCommand(String params) {
		cmd.performCommand(params);
		System.out.println("time: " + System.currentTimeMillis());
	}
	@Override
	protected void executeCommand() {		
	}	
}
