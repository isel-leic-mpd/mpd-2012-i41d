package calc;

import java.util.Stack;

public abstract class IntOperator implements IntExpression{
	final IntExpression exp1, exp2;

	public IntOperator(Stack<IntExpression> eval){
		this.exp1 = eval.pop();
		this.exp2 = eval.pop();
	} 
	
}
