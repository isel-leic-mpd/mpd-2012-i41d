package cmdsh.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import cmdsh.parsers.ArgsParserUnordered;

public class CmdWrapper extends AbstractCommand{

	private final Method cmdMethod;
	private final Object target;
	
	public CmdWrapper(String name, Object obj, Method m) {
		super(name, new ArgsParserUnordered(), new IArgument[0]);
		cmdMethod = m;
		if((m.getModifiers() & Modifier.STATIC) != 0){
			target = null;
		}else{
			target = obj;
		}
	}

	@Override
	protected void executeCommand() {
		try {
			cmdMethod.invoke(target);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}
