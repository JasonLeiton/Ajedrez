package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;
import java.util.ArrayList;

import static ASTMCore.metadata.Tags.CASE_BLOCK;

public class CaseBlock extends SwitchCase {

	public CaseBlock() {
		setTag(CASE_BLOCK);
	}

	/**
	 * <pre>
	 *           1..1          1..*
	 * CaseBlock ------------------------> Expression
	 *                   &lt;       caseExpression
	 * </pre>
	 */
	private ArrayList<Expression> caseExpression;

	public ArrayList<Expression> getCaseExpression() {
		if (this.caseExpression == null) {
			this.caseExpression = new ArrayList<Expression>();
		}
		return this.caseExpression;
	}
	
	public void accept(GASTVisitor v) {
		v.visitCaseBlock(this);
	}

}
