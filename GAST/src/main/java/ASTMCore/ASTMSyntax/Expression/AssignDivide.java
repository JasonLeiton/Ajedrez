package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ASSIGN_DIVIDE;

public class AssignDivide extends BinaryOperator {

	public AssignDivide() {
		setTag(ASSIGN_DIVIDE);
	}

	public void accept(GASTVisitor v) {
		v.visitAssignDivide(this);
	}

}
