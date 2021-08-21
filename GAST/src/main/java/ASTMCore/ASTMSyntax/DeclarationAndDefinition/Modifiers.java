package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.ASTMSyntax.MinorSyntaxObject;
import ASTMCore.visitor.GASTVisitor;

public abstract class Modifiers extends MinorSyntaxObject {
	
	public abstract void accept(GASTVisitor v);

}
