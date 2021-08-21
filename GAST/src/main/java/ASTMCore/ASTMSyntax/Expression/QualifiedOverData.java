package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.QUALIFIED_OVER_DATA;

public class QualifiedOverData extends QualifiedIdentifierReference {

	public QualifiedOverData() {
		setTag(QUALIFIED_OVER_DATA);
	}

	public void accept(GASTVisitor v) {
		v.visitQualifiedOverData(this);
	}
	
}
