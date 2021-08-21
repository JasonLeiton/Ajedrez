package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.DEFAULT_UNARY_OPERATOR;

public class DefaultUnaryOperator extends UnaryOperator {

	public DefaultUnaryOperator() {
		setTag(DEFAULT_UNARY_OPERATOR);
	}

	public void accept(GASTVisitor v) {
		v.visitDefaultUnaryOperator(this);
	}

}
