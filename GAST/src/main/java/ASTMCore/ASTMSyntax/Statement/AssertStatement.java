package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ASSERT_STATEMENT;

public class AssertStatement extends Statement{

	public AssertStatement() {
		setTag(ASSERT_STATEMENT);
	}

	private Expression expression;
	private Expression message;

	public Expression getExpression() {
		return expression;
	}
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	public Expression getMessage() {
		return message;
	}
	public void setMessage(Expression message) {
		this.message = message;
	}

	public void accept(GASTVisitor v) {
		v.visitAssertStatement(this);
	}

}
