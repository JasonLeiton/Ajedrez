package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ASSIGN_BIT_AND;

public class AssignBitAnd extends BinaryOperator {

	public AssignBitAnd() {
		setTag(ASSIGN_BIT_AND);
	}

	public void accept(GASTVisitor v) {
		v.visitAssignBitAnd(this);
	}

}
