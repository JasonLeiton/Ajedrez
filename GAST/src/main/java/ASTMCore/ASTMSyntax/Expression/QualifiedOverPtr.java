package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.QUALIFIED_OVER_PTR;

public class QualifiedOverPtr extends QualifiedIdentifierReference {

	public QualifiedOverPtr() {
		setTag(QUALIFIED_OVER_PTR);
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}
}
