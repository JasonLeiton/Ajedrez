package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.PARENTHESIZED_EXPRESSION;

public class ParenthesizedExpression extends Expression {

	public ParenthesizedExpression() {
		setTag(PARENTHESIZED_EXPRESSION);
	}

	private Expression internalExpression;

	public Expression getInternalExpression() {
		return internalExpression;
	}

	public void setInternalExpression(Expression internalExpression) {
		this.internalExpression = internalExpression;
	}
	
	public void accept(GASTVisitor v) {
		v.visitParenthesizedExpression(this);
	}

}
