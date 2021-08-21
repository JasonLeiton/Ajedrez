package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Name;
import ASTMCore.visitor.GASTVisitor;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.LabelDefinition;

import static ASTMCore.metadata.Tags.LABEL_ACCESS;

public class LabelAccess extends Expression {

	public LabelAccess() {
		setTag(LABEL_ACCESS);
	}

	private Name labelName;

	public void setLabelName(Name value) {
		this.labelName = value;
	}

	public Name getLabelName() {
		return this.labelName;
	}


	private LabelDefinition labelDefinition;

	public void setLabelDefinition(LabelDefinition value) {
		this.labelDefinition = value;
	}

	public LabelDefinition getLabelDefinition() {
		return this.labelDefinition;
	}

	@Override
	public void accept(GASTVisitor v) {
		v.visitLabelAccess(this);
	}
	
}
