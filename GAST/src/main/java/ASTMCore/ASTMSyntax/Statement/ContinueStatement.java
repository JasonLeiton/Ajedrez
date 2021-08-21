package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.Expression.LabelAccess;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.CONTINUE_STATEMENT;

public class ContinueStatement extends Statement {

	public ContinueStatement() {
		setTag(CONTINUE_STATEMENT);
	}

	/**
	 * <pre>
	 *           1..1          1..1
	 * ContinueStatement ------------------------> LabelAccess
	 *                   &lt;       target
	 * </pre>
	 */
	private LabelAccess target;

	public void setTarget(LabelAccess value) {
		this.target = value;
	}

	public LabelAccess getTarget() {
		return this.target;
	}
	
	public void accept(GASTVisitor v) {
		v.visitContinueStatement(this);

	}


}
