package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.INCREMENT;

public class Increment extends UnaryOperator {

	public Increment() {
		setTag(INCREMENT);
	}

	public void accept(GASTVisitor v) {
		v.visitIncrement(this);
	}
	
}
