package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.ASTMSyntax.Types.LabelType;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.LABEL_DEFINITION;

public class LabelDefinition extends DefintionObject {

	public LabelDefinition() {
		setTag(LABEL_DEFINITION);
	}

	private Name labelname;

	public void setLabelname(Name value) {
		this.labelname = value;
	}

	public Name getLabelname() {
		return this.labelname;
	}


	private LabelType labelType;

	public void setLabelType(LabelType value) {
		this.labelType = value;
	}

	public LabelType getLabelType() {
		return this.labelType;
	}

	@Override
	public void accept(GASTVisitor v) {
		v.visitLabelDefinition(this);
	}

}
