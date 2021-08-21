package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.RETURN_STATEMENT;

public class ReturnStatement extends Statement {

	public ReturnStatement() {
		setTag(RETURN_STATEMENT);
	}

	/**
	 * <pre>
	 *           1..1          1..1
	 * ReturnStatement ------------------------> Expression
	 *                   &lt;       returnValue
	 * </pre>
	 */
	private Expression returnValue;

	public void setReturnValue(Expression value) {
		this.returnValue = value;
	}

	public Expression getReturnValue() {
		return this.returnValue;
	}
	
	public void accept(GASTVisitor v) {
		v.visitReturnStatement(this);

	}

}
