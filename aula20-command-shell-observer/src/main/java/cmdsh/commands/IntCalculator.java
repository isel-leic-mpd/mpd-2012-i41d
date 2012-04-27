package cmdsh.commands;

import java.util.Stack;

import calc.ExptTerminal;
import calc.IntExpression;
import calc.OpAdd;
import calc.OpSub;
import cmdsh.args.ArgVar;
import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;
import cmdsh.parsers.ArgsParserOrdered;

public class IntCalculator extends AbstractCommand{
	final ArgVar args;
	public IntCalculator(String name) {
		super(name, new ArgsParserOrdered(), new IArgument[]{
			new ArgVar("args")
		});
		args = (ArgVar) getArgs().iterator().next();
		
	}
	public IntCalculator() {
		this("calc");
	}

	@Override
	protected void executeCommand() {
		Stack<IntExpression> eval = new Stack<IntExpression>();
		for (String a : args.getValue()) {
			char c = a.charAt(0);
			switch (c) {
			case '+':
				eval.push(new OpAdd(eval));
				break;
			case '-':
				eval.push(new OpSub(eval));
				break;
			default:
				eval.push(new ExptTerminal(a));
			}
		}
		System.out.println(eval.pop().execute());
	}
}
