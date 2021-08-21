package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ASSIGN_BIT_LEFT_SHIFT;

public class AssignBitLeftShift extends BinaryOperator {

	public AssignBitLeftShift() {
		setTag(ASSIGN_BIT_LEFT_SHIFT);
	}

	public void accept(GASTVisitor v) {
		v.visitAssignBitLeftShift(this);
	}

}
