package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.MinorSyntaxObject;
import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;
import java.util.List;

import static ASTMCore.metadata.Tags.SWITCH_CASE;

/**
 * Esta clase modela los casos de la estructura de control llamada "switch".
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class SwitchCase extends MinorSyntaxObject {

	public SwitchCase() {
		setTag(SWITCH_CASE);
	}

	private Boolean isEvaluateAllCases;

	public void setIsEvaluateAllCases(Boolean value) {
		this.isEvaluateAllCases = value;
	}

	public Boolean getIsEvaluateAllCases() {
		return this.isEvaluateAllCases;
	}

	/**
	 * <pre>
	 *           1..1          1..*
	 * SwitchCase ------------------------> Statement
	 *                   &lt;       body
	 * </pre>
	 */
	private ArrayList<Statement> body;
	private Expression evaluation;

	public Expression getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Expression evaluation) {
		this.evaluation = evaluation;
	}

	public ArrayList<Statement> getBody() {
		if (this.body == null) {
			this.body = new ArrayList<Statement>();
		}
		return this.body;
	}

	public void setBody(ArrayList<Statement> body) {
		this.body = body;
	}

	public void accept(GASTVisitor v) {
		v.visitSwitchCase(this);
	}
	
}
