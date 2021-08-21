package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ASSIGN_BIT_XOR;

public class AssignBitXor extends BinaryOperator {

	public AssignBitXor() {
		setTag(ASSIGN_BIT_XOR);
	}

	public void accept(GASTVisitor v) {
		v.visitAssignBitXor(this);
	}

}
