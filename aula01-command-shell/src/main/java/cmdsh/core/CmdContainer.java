package cmdsh.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class CmdContainer {
	/* ==========================================================
	 * *************************** FIELDS ***********************
	 * ==========================================================
	 */
	/** 
	 * @uml.property name="cmds"
	 * @uml.associationEnd multiplicity="(0 -1)" aggregation="shared"
	 */
	private final Map<String, Command> cmds;

	/* ==========================================================
	 * ***************************  CTOR  ***********************
	 * ==========================================================
	 */
	public CmdContainer(Command...inCmds){
		cmds = new HashMap<String, Command>();
		for (Command c : inCmds) {
			cmds.put(c.getName(), c);
		}
	}
	/* ==========================================================
	 * ************************** METHODS  **********************
	 * ==========================================================
	 */	
	public Command getCommand(String args){
		return cmds.get(args);
	} 
}