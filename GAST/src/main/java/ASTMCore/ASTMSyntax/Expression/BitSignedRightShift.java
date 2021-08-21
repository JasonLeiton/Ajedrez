package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.BIT_SIGNED_RIGHT_SHIFT;

public class BitSignedRightShift extends BinaryOperator {

	public BitSignedRightShift() {
		setTag(BIT_SIGNED_RIGHT_SHIFT);
	}

	public void accept(GASTVisitor v) {
		v.visitBitSignedRightShift(this);
	}
	
}
