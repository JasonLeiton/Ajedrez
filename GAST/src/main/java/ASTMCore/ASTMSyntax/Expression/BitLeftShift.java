package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.BIT_LEFT_SHIFT;

public class BitLeftShift extends BinaryOperator {

	public BitLeftShift() {
		setTag(BIT_LEFT_SHIFT);
	}

	public void accept(GASTVisitor v) {
		v.visitBitLeftShift(this);
	}
	
}
