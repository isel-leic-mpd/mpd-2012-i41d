package calc;

public class ExptTerminal implements IntExpression {
	final int val;
	public ExptTerminal(String arg){
		val = Integer.valueOf(arg);
	}
	
	@Override
	public int execute() {
		return val;
	}

}
