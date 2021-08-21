package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.STRING_LITERAL;

public class StringLiteral extends Literal {

	public StringLiteral() {
		setTag(STRING_LITERAL);
	}

	public void accept(GASTVisitor v) {
		v.visitStringLiteral(this);
	}
	
}
