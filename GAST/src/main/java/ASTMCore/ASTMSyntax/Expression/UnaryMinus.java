package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.UNARY_MINUS;

public class UnaryMinus extends UnaryOperator {

	public UnaryMinus() {
		setTag(UNARY_MINUS);
	}

	public void accept(GASTVisitor v) {
		v.visitUnaryMinus(this);
	}
	
}
