package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.DO_WHILE_STATEMENT;

public class DoWhileStatement extends LoopStatement {

	public DoWhileStatement() {
		setTag(DO_WHILE_STATEMENT);
	}

	public void accept(GASTVisitor v) {
		v.visitDoWhileStatement(this);

	}
}
