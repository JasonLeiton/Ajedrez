package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import static ASTMCore.metadata.Tags.ABSTRACT;

public class Abstrac extends AccessKind {

	public Abstrac() {
		setTag(ABSTRACT);
	}

	private String modifier="abstract";

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
}
