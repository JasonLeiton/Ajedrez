package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.MinorSyntaxObject;
import ASTMCore.visitor.GASTVisitor;

public abstract class ActualParameter extends MinorSyntaxObject {
	
	public abstract void accept(GASTVisitor v);
	
}
