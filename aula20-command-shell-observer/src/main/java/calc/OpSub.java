package calc;

import java.util.Stack;

public class OpSub extends IntOperator{
	public OpSub(Stack<IntExpression> eval){
		super(eval);
	}
	@Override
	public int execute() {
		return exp1.execute() - exp2.execute();
	}

}
