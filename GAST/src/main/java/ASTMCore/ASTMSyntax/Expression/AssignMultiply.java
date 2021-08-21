package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ASSIGN_MULTIPLY;

public class AssignMultiply extends BinaryOperator {

	public AssignMultiply() {
		setTag(ASSIGN_MULTIPLY);
	}

	public void accept(GASTVisitor v) {
		v.visitAssignMultiply(this);
	}

}
