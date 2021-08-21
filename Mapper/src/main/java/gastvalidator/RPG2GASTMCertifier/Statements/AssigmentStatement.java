package gastvalidator.RPG2GASTMCertifier.Statements;

import gastvalidator.RPG2GASTMCertifier.Expressions.Expression;
import gastvalidator.RPG2GASTMCertifier.Expressions.ExpressionNode;
import gastvalidator.RPG2GASTMCertifier.Expressions.ExpressionType;

public class AssigmentStatement extends Statement{
	
	private Expression result;
	private Expression expresion;
	
	public AssigmentStatement() {
		super();
	}
	
	public AssigmentStatement(String result, Expression expression) {
		super();
		setExpresion(expression);
		setResult(result);
	}
	
	public AssigmentStatement(String result, ExpressionType ptype, String expression) {
		super();
		setExpresion(new ExpressionNode(ptype,expression));
		setResult(result);
	}
	
	public AssigmentStatement(String result, ExpressionType ptype, Expression expression) {
		super();
		setExpresion(expression);
		setResult(result);
	}
	
	public AssigmentStatement(Expression result, ExpressionType ptype, Expression expression) {
		super();
		setExpresion(expression);
		setResult(result);
	}
	
	
	public Expression getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = new ExpressionNode(ExpressionType.NODE_EXPRESSION, result);
	}
	public void setResult(Expression result) {
		this.result = result;
	}
	public Expression getExpresion() {
		return expresion;
	}
	public void setExpresion(Expression expresion) {
		this.expresion = expresion;
	}
	

}
