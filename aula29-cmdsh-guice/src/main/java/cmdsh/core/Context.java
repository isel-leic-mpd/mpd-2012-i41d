package cmdsh.core;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Set;

import com.google.inject.Inject;

public class Context implements IContext{
	/* ==========================================================
	 * *************************** FIELDS ***********************
	 * ==========================================================
	 */
	/**
	 * @uml.property   name="cmds"
	 * @uml.associationEnd   multiplicity="(0 -1)" aggregation="shared" qualifier="key:java.lang.String cmdsh.core.IContext"
	 */
	private final Map<String, IContext> ctxs;
	private final String name;

	/* ==========================================================
	 * ***************************  CTOR  ***********************
	 * ==========================================================
	 */
	public Context(String name){
		this.name = name;
		ctxs = new HashMap<String, IContext>();
	}
	@Inject
	public Context(@RootCtxName String name, @RootCmds Set<Object> inCmds){
		this(name);
		for (Object obj : inCmds) {
			if(obj instanceof AbstractCommand)
				add((AbstractCommand) obj);
			else{
				try {
					Method [] ms = obj.getClass().getMethods();
					for (Method m : ms) {
						CmdMethod anot = m.getAnnotation(CmdMethod.class);
						if(anot != null){
							add(new CmdWrapper(anot.name(), obj, m));
						}
					}
				} catch (SecurityException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	public void add(IContext c) {
		String[] name = c.getName().split(" ");
		add(c, name, 0);
	}

	public void add(IContext c, String[] name, int from) {
		if(from == (name.length-1)){
			ctxs.put(name[from], c);
			return;
		}
		IContext curr = ctxs.get(name[from]);
		if(curr == null){
			curr = new Context(name[from]);
			ctxs.put(name[from], curr);
		}
		curr.add(c, name,  ++from);
	}
	/* ==========================================================
	 * ************************** METHODS  **********************
	 * ==========================================================
	 */	
	public IContext getContext(final String cmdName){
		String [] aux = cmdName.split(" ");
		return get(aux, 0);
	}
	public IContext get(String[] aux, int from) {
		if(from >= aux.length){
			throw new NoSuchCommandException();
		}
		IContext curr = ctxs.get(aux[from]);
		if(curr == null) throw new NoSuchCommandException();
		if(from == (aux.length-1)) return curr;
		return curr.get(aux, ++from);
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void performCommand(String params) {
		for (IContext ctx : ctxs.values()) {
			System.out.println(ctx.getName());
		}
	}
	@Override
	public Iterable<IArgument> getArgs() {
		throw new UnsupportedOperationException();
	} 
}