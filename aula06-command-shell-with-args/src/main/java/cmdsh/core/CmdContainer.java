package cmdsh.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

import collect.IterUtils;
import collect.Predicate;

public class CmdContainer {
	/* ==========================================================
	 * *************************** FIELDS ***********************
	 * ==========================================================
	 */
	/**
	 * @uml.property   name="cmds"
	 * @uml.associationEnd   multiplicity="(0 -1)" aggregation="shared" qualifier="key:java.lang.String cmdsh.core.ICommand"
	 */
	private final List<ICommand> cmds;

	/* ==========================================================
	 * ***************************  CTOR  ***********************
	 * ==========================================================
	 */
	public CmdContainer(ICommand...inCmds){
		cmds = new LinkedList<ICommand>();
		for (ICommand c : inCmds) {
			cmds.add(c);
		}
	}
	/* ==========================================================
	 * ************************** METHODS  **********************
	 * ==========================================================
	 */	
	public ICommand getCommand(final String cmdName){
		ICommand c = IterUtils.find(cmds, new Predicate<ICommand>(){public boolean invoke(ICommand item) {
			return cmdName.indexOf(item.getName()) == 0;
		}});
		if(c == null) throw new NoSuchCommandException();
		else return c;
	} 
}