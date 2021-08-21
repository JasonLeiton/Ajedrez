package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.BIT_XOR;

public class BitXor extends BinaryOperator {

	public BitXor() {
		setTag(BIT_XOR);
	}

	public void accept(GASTVisitor v) {
		v.visitBitXor(this);
	}
	
}
