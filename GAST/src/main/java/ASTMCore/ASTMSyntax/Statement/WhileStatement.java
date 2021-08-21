package ASTMCore.ASTMSyntax.Statement;

import java.util.ArrayList;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.WHILE_STATEMENT;

public class WhileStatement extends LoopStatement {

    /**
     * Used to set at end of cicle conditional block
     *
     * @param EndStmt Statements within the block
     */
	private ArrayList<Statement> EndStmt;

	public WhileStatement() {
		setTag(WHILE_STATEMENT);
	}

	public void accept(GASTVisitor v) {
		v.visitWhileStatement(this);

	}

	/**
     * Getter of the end statement
     *
     * @return List with the statements
     */
	public ArrayList<Statement> getEndStmt() {
		return EndStmt;
	}

	/**
     * Setter of the end statement list
     *
     * @param endStmt List with the statements
     */
	public void setEndStmt(ArrayList<Statement> endStmt) {
		EndStmt = endStmt;
	}
	
}
