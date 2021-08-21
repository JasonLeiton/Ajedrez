package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.DEREF;

public class Deref extends UnaryOperator {

	public Deref() {
		setTag(DEREF);
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}
}
