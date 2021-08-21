package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ASSIGN_MODULUS;

public class AssignModulus extends BinaryOperator {

	public AssignModulus() {
		setTag(ASSIGN_MODULUS);
	}

	public void accept(GASTVisitor v) {
		v.visitAssignModulus(this);
	}

}
