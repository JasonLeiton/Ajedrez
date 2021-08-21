package ASTMCore.ASTMSyntax.Types;

import ASTMCore.visitor.GASTVisitor;

public abstract class TypeReference extends Type {
	
	public abstract void accept(GASTVisitor v);
}
