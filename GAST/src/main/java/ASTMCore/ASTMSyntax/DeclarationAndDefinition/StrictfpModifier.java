package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.STRICT_FP_MODIFIER;

public class StrictfpModifier extends Modifiers {

	public StrictfpModifier() {
		setTag(STRICT_FP_MODIFIER);
	}

	private String modifier="strictfp";

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	@Override
	public void accept(GASTVisitor v) {
		v.visitStrictfpModifier(this);
	}
}
