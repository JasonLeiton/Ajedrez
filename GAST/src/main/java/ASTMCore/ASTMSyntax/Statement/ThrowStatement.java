package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.THROW_STATEMENT;

public class ThrowStatement extends Statement {

	public ThrowStatement() {
		setTag(THROW_STATEMENT);
	}

	/**
	 * <pre>
	 *           1..1          1..1
	 * ThrowStatement ------------------------> Expression
	 *                   &lt;       exception
	 * </pre>
	 */
	private Expression exception;
	
	private Expression from;

	public void setException(Expression value) {
		this.exception = value;
	}

	public Expression getException() {
		return this.exception;
	}
	
	public void accept(GASTVisitor v) {
		v.visitThrowStatement(this);

	}

	public Expression getFrom() {
		return from;
	}

	public void setFrom(Expression from) {
		this.from = from;
	}

}
