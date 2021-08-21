package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.LabelDefinition;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.LABELED_STATEMENT;

public class LabeledStatement extends Statement {

	public LabeledStatement() {
		setTag(LABELED_STATEMENT);
	}

	/**
	 * <pre>
	 *           1..1          1..1
	 * LabeledStatement ------------------------> LabelDefinition
	 *                   &lt;       label
	 * </pre>
	 */
	private LabelDefinition label;

	public void setLabel(LabelDefinition value) {
		this.label = value;
	}

	public LabelDefinition getLabel() {
		return this.label;
	}

	/**
	 * <pre>
	 *           1..1          1..1
	 * LabeledStatement ------------------------> Statement
	 *                   &lt;       statement
	 * </pre>
	 */
	private Statement statement;

	public void setStatement(Statement value) {
		this.statement = value;
	}

	public Statement getStatement() {
		return this.statement;
	}

	public void accept(GASTVisitor v) {
		v.visitLabeledStatement(this);

	}

}
