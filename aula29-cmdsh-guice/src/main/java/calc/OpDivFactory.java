package calc;

import java.util.Stack;

public class OpDivFactory implements IntExpressionFactory{

	@Override
	public IntExpression make(Stack<IntExpression> stack) {
		return new  OpDiv(stack);
	}

	@Override
	public String getSymbol() {
		return "/";
	}

}
