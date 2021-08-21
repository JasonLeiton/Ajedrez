package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;

import java.util.List;

import static ASTMCore.metadata.Tags.SWITCH_STATEMENT;

/**
 * Esta clase es la que modela un switch.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class SwitchStatement extends Statement {

	public SwitchStatement() {
		setTag(SWITCH_STATEMENT);
	}

	/**
	 * This attribute is to model switch statement
	 */
	private Expression switchExpression;

	public void setSwitchExpression(Expression value) {
		this.switchExpression = value;
	}

	public Expression getSwitchExpression() {
		return this.switchExpression;
	}
	/**
	 * This attribute is to model whole switch cases
	 */ 
	private List<SwitchCase> cases;

	public List<SwitchCase> getCases() {
		if (this.cases == null) { // verifies if it is null, it will be initialized
			this.cases = new ArrayList<SwitchCase>();
		}
		return this.cases;
	}

	public void setCases(List<SwitchCase> cases) {
		this.cases = cases;
	}

	public void accept(GASTVisitor v) {
		v.visitSwitchStatement(this);
	}

}
