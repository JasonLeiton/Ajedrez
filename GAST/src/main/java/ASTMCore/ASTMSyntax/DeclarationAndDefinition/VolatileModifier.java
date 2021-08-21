package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.VOLATILE_MODIFIER;

public class VolatileModifier extends Modifiers{

	public VolatileModifier() {
		setTag(VOLATILE_MODIFIER);
	}

	private String modifier="volatile";

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	@Override
	public void accept(GASTVisitor v) {
		v.visitVolatileModifier(this);
	}
}
