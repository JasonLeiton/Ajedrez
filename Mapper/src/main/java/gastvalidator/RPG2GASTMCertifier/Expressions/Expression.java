package gastvalidator.RPG2GASTMCertifier.Expressions;

import gastvalidator.RPG2GASTMCertifier.IRpgSyntaxElement;



public class Expression implements IRpgSyntaxElement{
	
	private ExpressionType _type;
	
	public Expression(ExpressionType ptype) {
		_type = ptype;
	}
	
	public boolean isFunctionCallExpression() {
		return _type == ExpressionType.FUNCTION_CALL_EXPRESSION;
	}
	public boolean isArithmeticalExpression() {
		return _type == ExpressionType.ARITMETICAL_EXPRESSION;
	}
	public boolean isLogicalExpression() {
		return _type == ExpressionType.LOGICAL_EXPRESSION;
	}
	public boolean isNodeExpression() {
		return _type == ExpressionType.NODE_EXPRESSION;
	}
	public boolean isParenthesizedExpression() {
		return _type == ExpressionType.PARENTHESIZED_EXPRESSION;
	}

}
