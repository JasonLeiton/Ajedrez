package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.BIT_UNSIGNED_RIGHT_SHIFT;

public class BitUnsignedRightShift extends BinaryOperator {

	public BitUnsignedRightShift() {
		setTag(BIT_UNSIGNED_RIGHT_SHIFT);
	}

	public void accept(GASTVisitor v) {
		v.visitBitUnsignedRightShift(this);
	}
	
}
