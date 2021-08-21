package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.REAL_LITERAL;

public class RealLiteral extends Literal {

	public RealLiteral() {
		setTag(REAL_LITERAL);
	}

	public void accept(GASTVisitor v) {
		v.visitRealLiteral(this);
	}
	
}
