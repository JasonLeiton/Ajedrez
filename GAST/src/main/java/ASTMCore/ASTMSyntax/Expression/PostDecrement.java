package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.POST_DECREMENT;

public class PostDecrement extends UnaryOperator {

	public PostDecrement() {
		setTag(POST_DECREMENT);
	}

	public void accept(GASTVisitor v) {
		v.visitPostDecrement(this);
	}
	
}
