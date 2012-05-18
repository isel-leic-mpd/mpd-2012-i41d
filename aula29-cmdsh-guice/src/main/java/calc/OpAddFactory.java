package calc;

import java.util.Stack;

public class OpAddFactory implements IntExpressionFactory{

	@Override
	public IntExpression make(Stack<IntExpression> stack) {
		return new  OpAdd(stack);
	}

	@Override
	public String getSymbol() {
		return "+";
	}

}
