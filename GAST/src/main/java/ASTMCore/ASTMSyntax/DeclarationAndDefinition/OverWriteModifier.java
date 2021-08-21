package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

public class OverWriteModifier extends Modifiers {

	private String modifier="override";

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}

}
