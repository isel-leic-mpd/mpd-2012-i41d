package calc;

import java.util.Stack;

public class OpSubFactory implements IntExpressionFactory{

	@Override
	public IntExpression make(Stack<IntExpression> stack) {
		return new  OpSub(stack);
	}

	@Override
	public String getSymbol() {
		return "-";
	}

}
