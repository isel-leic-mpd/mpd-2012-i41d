package calc;

import java.util.Stack;

public class OpAdd extends IntOperator{
	public OpAdd(Stack<IntExpression> eval){
		super(eval);
	}
	@Override
	public int execute() {
		return exp1.execute() + exp2.execute();
	}

}
