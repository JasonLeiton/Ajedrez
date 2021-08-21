package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ABSTRACT_MODIFIER;

public class AbstractModifier extends Modifiers {

	public AbstractModifier() {
		setTag(ABSTRACT_MODIFIER);
	}

	private String modifier="abstract";

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	@Override
	public void accept(GASTVisitor v) {
		v.visitAbstractModifier(this);
	}
}
