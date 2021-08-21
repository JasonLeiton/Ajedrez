package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.PUBLIC_MODIFIER;

public class PublicModifier extends Modifiers {

	public PublicModifier() {
		setTag(PUBLIC_MODIFIER);
	}

	private String modifier="public";

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	@Override
	public void accept(GASTVisitor v) {
		v.visitPublicModifier(this);
	}
	
}
