package calc;

import java.util.Stack;

public class OpDiv extends IntOperator{
	public OpDiv(Stack<IntExpression> eval){
		super(eval);
	}
	@Override
	public int execute() {
		return exp1.execute() / exp2.execute();
	}

}
