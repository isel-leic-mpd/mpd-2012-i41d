package calc;

import java.util.Stack;

public class OpSub implements IntExpression {
	final int val;
	public OpSub(Stack<IntExpression> eval){
		val = eval.pop().execute() - eval.pop().execute(); 
	}
	@Override
	public int execute() {
		return val;
	}

}
