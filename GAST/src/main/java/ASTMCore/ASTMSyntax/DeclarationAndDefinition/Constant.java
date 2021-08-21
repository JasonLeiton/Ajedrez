package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import static ASTMCore.metadata.Tags.CONSTANT;

public class Constant extends AccessKind {

	public Constant() {
		setTag(CONSTANT);
	}

	private String modifier="constant";

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
}
