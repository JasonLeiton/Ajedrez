package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.NULL_LITERAL;

public class NullLiteral extends Literal {

	public NullLiteral() {
		setTag(NULL_LITERAL);
	}

	public void accept(GASTVisitor v) {
		v.visitNullLiteral(this);
	}
	
}
