package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.CHAR_LITERAL;

public class CharLiteral extends Literal {

	public CharLiteral() {
		setTag(CHAR_LITERAL);
	}

	public void accept(GASTVisitor v) {
		v.visitCharLiteral(this);
	}
	
}
