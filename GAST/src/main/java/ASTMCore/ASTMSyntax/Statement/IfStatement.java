package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.IF_STATEMENT;

public class IfStatement extends Statement {

	public IfStatement() {
		setTag(IF_STATEMENT);
	}

	/**
	 * <pre>
	 *           1..1          1..1
	 * IfStatement ------------------------> Expression
	 *                   &lt;       condition
	 * </pre>
	 */
	private Expression condition;

	public void setCondition(Expression value) {
		this.condition = value;
	}

	public Expression getCondition() {
		return this.condition;
	}

	/**
	 * <pre>
	 *           1..1          1..1
	 * IfStatement ------------------------> Statement
	 *                   &lt;       thenBody
	 * </pre>
	 */
	private Statement thenBody;

	public void setThenBody(Statement value) {
		this.thenBody = value;
	}

	public Statement getThenBody() {
		return this.thenBody;
	}

	/**
	 * <pre>
	 *           1..1          1..1
	 * IfStatement ------------------------> Statement
	 *                   &lt;       elseBody
	 * </pre>
	 */
	private Statement elseBody;

	public void setElseBody(Statement value) {
		this.elseBody = value;
	}

	public Statement getElseBody() {
		return this.elseBody;
	}
	
	public void accept(GASTVisitor v) {
		v.visitIfStatement(this);

	}

}
