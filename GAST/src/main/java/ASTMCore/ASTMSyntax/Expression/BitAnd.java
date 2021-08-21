package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.BIT_AND;

public class BitAnd extends BinaryOperator {

	public BitAnd() {
		setTag(BIT_AND);
	}

	public void accept(GASTVisitor v) {
		v.visitBitAnd(this);
	}
	
}
