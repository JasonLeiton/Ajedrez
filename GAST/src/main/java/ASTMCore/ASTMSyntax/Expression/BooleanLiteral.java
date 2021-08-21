package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.BOOLEAN_LITERAL;

public class BooleanLiteral extends Literal {

	public BooleanLiteral() {
		setTag(BOOLEAN_LITERAL);
	}

	public void accept(GASTVisitor v) {
		v.visitBooleanLiteral(this);
	}
	
}
