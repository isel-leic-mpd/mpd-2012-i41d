package cmdsh.commands;

public class IntCalculatorPrintTime extends IntCalculator{
	public IntCalculatorPrintTime(){
		super("calc2");
	}
	@Override
	protected void executeCommand() {
		super.executeCommand();
		System.out.println("time: " + System.currentTimeMillis());
	}
	
}
