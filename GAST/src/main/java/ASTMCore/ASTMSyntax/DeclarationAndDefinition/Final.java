package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import static ASTMCore.metadata.Tags.FINAL;

public class Final extends AccessKind {

	public Final() {
		setTag(FINAL);
	}

	private String modifier="Final";

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
}
