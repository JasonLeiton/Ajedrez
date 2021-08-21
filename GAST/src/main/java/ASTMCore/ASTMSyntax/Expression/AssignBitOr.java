package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ASSIGN_BIT_OR;

public class AssignBitOr extends BinaryOperator {

	public AssignBitOr() {
		setTag(ASSIGN_BIT_OR);
	}

	public void accept(GASTVisitor v) {
		v.visitAssignBitOr(this);
	}

}
