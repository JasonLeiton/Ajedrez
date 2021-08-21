package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.GASTMSyntaxObject;
import ASTMCore.visitor.GASTVisitor;

public abstract class Statement extends GASTMSyntaxObject {
	
	public abstract void accept(GASTVisitor v);
	
}
