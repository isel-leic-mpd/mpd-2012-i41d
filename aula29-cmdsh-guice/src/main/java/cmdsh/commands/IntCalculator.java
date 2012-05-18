package cmdsh.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.google.inject.Inject;

import calc.ExptTerminal;
import calc.IntExpression;
import calc.IntExpressionFactory;
import calc.OpAdd;
import calc.OpSub;
import cmdsh.args.ArgVar;
import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;
import cmdsh.parsers.ArgsParserOrdered;

public class IntCalculator extends AbstractCommand{
	final ArgVar args;
	final Map<String, IntExpressionFactory> expres = new HashMap<String, IntExpressionFactory>();
	
	public IntCalculator(String name) {
		super(name, new ArgsParserOrdered(), new IArgument[]{
			new ArgVar("args")
		});
		args = (ArgVar) getArgs().iterator().next();
		
	}
	@Inject
	public IntCalculator(Set<IntExpressionFactory> factories) {
		this("calc");
		for (IntExpressionFactory e : factories) {
			expres.put(e.getSymbol(), e);
		}
	}

	@Override
	protected void executeCommand() {
		Stack<IntExpression> eval = new Stack<IntExpression>();
		for (String a : args.getValue()) {
			IntExpressionFactory f = expres.get(a);
			if(f != null)
				eval.push(f.make(eval));
			else
				eval.push(new ExptTerminal(a));
		}
		System.out.println(eval.pop().execute());
	}
}
