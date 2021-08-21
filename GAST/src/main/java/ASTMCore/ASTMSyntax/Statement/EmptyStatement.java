package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.EMPTY_STATEMENT;

public class EmptyStatement extends Statement {

	public EmptyStatement() {
		setTag(EMPTY_STATEMENT);
	}

	public void accept(GASTVisitor v) {
		v.visitEmptyStatement(this);

	}
	
}
