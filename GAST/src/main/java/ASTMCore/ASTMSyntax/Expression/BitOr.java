package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.BIT_OR;

public class BitOr extends BinaryOperator {

	public BitOr() {
		setTag(BIT_OR);
	}

	public void accept(GASTVisitor v) {
		v.visitBitOr(this);
	}
	
}
