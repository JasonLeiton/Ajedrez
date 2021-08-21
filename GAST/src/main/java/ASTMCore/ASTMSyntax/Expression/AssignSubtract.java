package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ASSIGN_SUBTRACT;

public class AssignSubtract extends BinaryOperator {

	public AssignSubtract() {
		setTag(ASSIGN_SUBTRACT);
	}

	public void accept(GASTVisitor v) {
		v.visitAssignSubtract(this);
	}

}
