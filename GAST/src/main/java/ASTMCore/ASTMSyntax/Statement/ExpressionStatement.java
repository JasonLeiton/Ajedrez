package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.EXPRESSION_STATEMENT;

public class ExpressionStatement extends Statement {

	public ExpressionStatement() {
		setTag(EXPRESSION_STATEMENT);
	}

	/**
	 * <pre>
	 *           1..1          1..1
	 * ExpressionStatement ------------------------> Expression
	 *                   &lt;       expression
	 * </pre>
	 */
	private Expression expression;

	public void setExpression(Expression value) {
		this.expression = value;
	}

	public Expression getExpression() {
		return this.expression;
	}
	
	public void accept(GASTVisitor v) {
		v.visitExpressionStatement(this);

	}

}
