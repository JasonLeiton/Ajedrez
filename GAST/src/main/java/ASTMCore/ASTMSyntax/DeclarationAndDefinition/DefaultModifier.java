package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.DEFAULT_MODIFIER;

public class DefaultModifier extends Modifiers {

	public DefaultModifier() {
		setTag(DEFAULT_MODIFIER);
	}

	private String modifier="default";

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	@Override
	public void accept(GASTVisitor v) {
		v.visitDefaultModifier(this);
	}
}
