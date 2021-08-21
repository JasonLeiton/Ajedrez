package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Name;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.DefintionObject;

public abstract class NameReference extends Expression {

	private Name identifierName;

	public void setIdentifierName(Name value) {
		this.identifierName = value;
	}

	public Name getIdentifierName() {
		return this.identifierName;
	}


	private DefintionObject refersTo;

	public void setRefersTo(DefintionObject value) {
		this.refersTo = value;
	}

	public DefintionObject getRefersTo() {
		return this.refersTo;
	}

}
