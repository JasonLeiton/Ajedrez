package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.PROTECTED_MODIFIER;

public class ProtectedModifier extends Modifiers {

	public ProtectedModifier() {
		setTag(PROTECTED_MODIFIER);
	}

	private String modifier="protected";

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	@Override
	public void accept(GASTVisitor v) {
		v.visitProtectedModifier(this);
	}
}
