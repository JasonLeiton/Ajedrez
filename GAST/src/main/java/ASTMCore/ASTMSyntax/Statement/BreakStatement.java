package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.Expression.LabelAccess;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.BREAK_STATEMENT;

public class BreakStatement extends Statement {

	public BreakStatement() {
		setTag(BREAK_STATEMENT);
	}

	/**
	 * <pre>
	 *           1..1          1..1
	 * BreakStatement ------------------------> LabelAccess
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
		v.visitBreakStatement(this);

	}

}
