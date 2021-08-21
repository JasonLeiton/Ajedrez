package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.FINAL_MODIFIER;

public class FinalModifier extends Modifiers {

	public FinalModifier() {
		setTag(FINAL_MODIFIER);
	}

	private String modifier="final";

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	@Override
	public void accept(GASTVisitor v) {
		v.visitFinalModifier(this);
	}
}
