package calc;

import java.util.Stack;

public class OpAdd implements IntExpression {
	final int val;
	public OpAdd(Stack<IntExpression> eval){
		val = eval.pop().execute() + eval.pop().execute(); 
	}
	@Override
	public int execute() {
		return val;
	}

}
