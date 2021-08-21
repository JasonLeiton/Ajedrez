package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.IDENTIFIER_REFERENCE;

public class IdentifierReference extends NameReference {

	public IdentifierReference() {
		setTag(IDENTIFIER_REFERENCE);
	}

	public void accept(GASTVisitor v) {
		v.visitIdentifierReference(this);
	}
	
}
