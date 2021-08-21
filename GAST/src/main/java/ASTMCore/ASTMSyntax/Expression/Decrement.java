package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.DECREMENT;

public class Decrement extends UnaryOperator {

	public Decrement() {
		setTag(DECREMENT);
	}

	public void accept(GASTVisitor v) {
		v.visitDecrement(this);
	}
	
}
