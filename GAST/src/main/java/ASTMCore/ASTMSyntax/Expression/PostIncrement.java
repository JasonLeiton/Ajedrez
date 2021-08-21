package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.POST_INCREMENT;

public class PostIncrement extends UnaryOperator {

	public PostIncrement() {
		setTag(POST_INCREMENT);
	}

	public void accept(GASTVisitor v) {
		v.visitPostIncrement(this);
	}
	
}
