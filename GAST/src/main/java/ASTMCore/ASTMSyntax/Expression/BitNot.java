package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.BIT_NOT;

public class BitNot extends UnaryOperator {

	public BitNot() {
		setTag(BIT_NOT);
	}

	public void accept(GASTVisitor v) {
		v.visitBitNot(this);
	}
	
}
