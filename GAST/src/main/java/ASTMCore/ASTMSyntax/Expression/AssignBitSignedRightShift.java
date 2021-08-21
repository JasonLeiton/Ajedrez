package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ASSIGN_BIT_SIGNED_RIGHT_SHIFT;

public class AssignBitSignedRightShift extends BinaryOperator {

	public AssignBitSignedRightShift() {
		setTag(ASSIGN_BIT_SIGNED_RIGHT_SHIFT);
	}

	public void accept(GASTVisitor v) {
		v.visitAssignBitSignedRightShift(this);
	}

}
