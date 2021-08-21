package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;
/*
 * Esta clase modela el modificador Global usado en python
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class GlobalModifier extends Modifiers {
	
	private String modifier="global";

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