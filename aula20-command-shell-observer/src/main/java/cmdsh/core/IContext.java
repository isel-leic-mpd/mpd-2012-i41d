package cmdsh.core;

public interface IContext {
	String getName();
	void performCommand(String params);
	Iterable<IArgument> getArgs();
	void add(IContext c, String[] name, int from);
	IContext get(String[] aux, int from);
}
