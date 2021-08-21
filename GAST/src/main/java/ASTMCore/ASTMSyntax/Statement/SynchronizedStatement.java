package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.SYNCHRONIZED_STATEMENT;

public class SynchronizedStatement extends Statement {

	public SynchronizedStatement() {
		setTag(SYNCHRONIZED_STATEMENT);
	}

	private Expression expression;
	private Statement body;
	
	public Expression getExpression() {
		return expression;
	}
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	public Statement getBody() {
		return body;
	}
	public void setBody(Statement body) {
		this.body = body;
	}

	public void accept(GASTVisitor v) {
		v.visitSynchronizedStatement(this);
	}
	
}
