package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.INTEGER_LITERAL;

public class IntegerLiteral extends Literal {

	public IntegerLiteral() {
		setTag(INTEGER_LITERAL);
	}

	public void accept(GASTVisitor v) {
		v.visitIntegerLiteral(this);
	}
	
}
