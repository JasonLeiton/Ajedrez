package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.TERMINATE_STATEMENT;

public class TerminateStatement extends Statement {

	public TerminateStatement() {
		setTag(TERMINATE_STATEMENT);
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}
}
