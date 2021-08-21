package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ASSIGN_ADD;

public class AssignAdd extends BinaryOperator {

	public AssignAdd() {
		setTag(ASSIGN_ADD);
	}

	public void accept(GASTVisitor v) {
		v.visitAssignAdd(this);
	}

}
