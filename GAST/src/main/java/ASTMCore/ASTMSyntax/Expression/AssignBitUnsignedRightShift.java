package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ASSIGN_BIT_UNSIGNED_RIGHT_SHIFT;

public class AssignBitUnsignedRightShift extends BinaryOperator {

	public AssignBitUnsignedRightShift() {
		setTag(ASSIGN_BIT_UNSIGNED_RIGHT_SHIFT);
	}

	public void accept(GASTVisitor v) {
		v.visitAssignBitUnsignedRightShift(this);
	}

}
