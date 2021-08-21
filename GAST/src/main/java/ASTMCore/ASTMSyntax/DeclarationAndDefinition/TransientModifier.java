package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.TRANSIENT_MODIFIER;

public class TransientModifier extends Modifiers {

	public TransientModifier() {
		setTag(TRANSIENT_MODIFIER);
	}

	private String modifier="transient";

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	@Override
	public void accept(GASTVisitor v) {
		v.visitTransientModifier(this);
	}
}
