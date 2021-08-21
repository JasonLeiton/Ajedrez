package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.PREFIX_UNARY_EXPRESSION;

public class PrefixUnaryExpression extends Expression {

	public PrefixUnaryExpression() {
		setTag(PREFIX_UNARY_EXPRESSION);
	}

	private UnaryOperator operator;

	public void setOperator(UnaryOperator value) {
		this.operator = value;
	}

	public UnaryOperator getOperator() {
		return this.operator;
	}


	private Expression operand;

	public void setOperand(Expression value) {
		this.operand = value;
	}

	public Expression getOperand() {
		return this.operand;
	}
	
	public void accept(GASTVisitor v) {
		v.visitPrefixUnaryExpression(this);
	}
		
}