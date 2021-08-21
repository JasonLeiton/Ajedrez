package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.PRIVATE_MODIFIER;

public class PrivateModifier extends Modifiers {

	public PrivateModifier() {
		setTag(PRIVATE_MODIFIER);
	}

	private String modifier="private";

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	@Override
	public void accept(GASTVisitor v) {
		v.visitPrivateModifier(this);
	}
}
