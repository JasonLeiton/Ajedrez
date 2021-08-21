package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.UNARY_PLUS;

public class UnaryPlus extends UnaryOperator {

	public UnaryPlus() {
		setTag(UNARY_PLUS);
	}

	public void accept(GASTVisitor v) {
		v.visitUnaryPlus(this);
	}
	
}
