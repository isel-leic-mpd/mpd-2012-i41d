package calc;

import java.util.Stack;

public interface IntExpressionFactory {
	String getSymbol();
	IntExpression make(Stack<IntExpression> stack);
}
