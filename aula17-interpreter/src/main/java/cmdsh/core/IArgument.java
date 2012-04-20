package cmdsh.core;

public interface IArgument<T>{
	String getName();
	T getValue();
	int parse(String[] args, int from );
}
