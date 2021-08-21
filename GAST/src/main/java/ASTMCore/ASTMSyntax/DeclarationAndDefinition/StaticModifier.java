package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.STATIC_MODIFIER;

public class StaticModifier extends Modifiers {

	public StaticModifier() {
		setTag(STATIC_MODIFIER);
	}

	private String modifier="static";

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	
	@Override
	public void accept(GASTVisitor v) {
		v.visitStaticModifier(this);
	}
}
