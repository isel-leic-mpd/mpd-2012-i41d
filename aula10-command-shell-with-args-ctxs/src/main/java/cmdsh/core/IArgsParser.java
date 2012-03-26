package cmdsh.core;

/**
 * Representa a estrategia de parsing dos parametros.
 */
public interface IArgsParser {
	void parse(String [] aParams, Iterable<IArgument> args);
}
