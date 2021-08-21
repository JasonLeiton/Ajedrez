package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.NOT;

public class Not extends UnaryOperator {

	public Not() {
		setTag(NOT);
	}

	public void accept(GASTVisitor v) {
		v.visitNot(this);
	}
	
}
