package cmdsh.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cmdsh.parsers.ArgsParserOrdered;

public class CmdPrintTime extends AbstractCommand{
	private final AbstractCommand cmd;
	private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private static final Calendar cal = Calendar.getInstance();
	public CmdPrintTime(AbstractCommand cmd) {
		super(cmd.name, new ArgsParserOrdered(), new IArgument[]{});
		this.cmd = cmd;
	}
	@Override
	public void performCommand(String params) {
		cmd.performCommand(params);
		System.out.println("time: " + dateFormat.format(cal.getTime()));
	}
	@Override
	protected void executeCommand() {		
	}	
}
