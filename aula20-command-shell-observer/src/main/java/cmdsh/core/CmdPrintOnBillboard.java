package cmdsh.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import guiutil.Billboard;
import cmdsh.parsers.ArgsParserOrdered;

public class CmdPrintOnBillboard extends AbstractCommand{
	private final AbstractCommand cmd;
	private final Billboard bb;
	private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private static final Calendar cal = Calendar.getInstance();

	
	public CmdPrintOnBillboard(AbstractCommand cmd, Billboard bb) {
		super(cmd.name, new ArgsParserOrdered(), new IArgument[]{});
		this.cmd = cmd;
		this.bb = bb;
	}
	@Override
	public void performCommand(String params) {
		cmd.performCommand(params);
		bb.addText("performed: " + cmd.getName() + "\n");
		bb.addText(dateFormat.format(cal.getTime()) + "\n");
		bb.addText("-----------------------------------\n");
		
	}
	@Override
	protected void executeCommand() {		
	}	

}
