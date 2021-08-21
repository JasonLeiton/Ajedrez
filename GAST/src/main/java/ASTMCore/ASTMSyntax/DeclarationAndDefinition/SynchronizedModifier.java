package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.SYNCHRONIZED_MODIFIER;

public class SynchronizedModifier extends Modifiers {

	public SynchronizedModifier() {
		setTag(SYNCHRONIZED_MODIFIER);
	}

	private String modifier="synchronized";

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	@Override
	public void accept(GASTVisitor v) {
		v.visitSynchronizedModifier(this);
	}
}
