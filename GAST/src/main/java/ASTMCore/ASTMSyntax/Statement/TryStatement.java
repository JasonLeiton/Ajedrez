package ASTMCore.ASTMSyntax.Statement;


import java.util.ArrayList;
import java.util.List;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.TRY_STATEMENT;

/**
 * Esta clase modela la estructura de manejo de excepciones "Try".
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class TryStatement extends Statement {

	public TryStatement() {
		setTag(TRY_STATEMENT);
	}

	private Statement body;

	private ArrayList<Statement> EndStmt;
	
	public Statement getBody() {
		return body;
	}

	public void setBody(Statement body) {
		this.body = body;
	}

	/**
	 * <pre>
	 *           1..1          1..*
	 * TryStatement ------------------------> CatchBlock
	 *                   &lt;       catchBlocks
	 * </pre>
	 */
	private List<CatchBlock> catchBlocks;

	public List<CatchBlock> getCatchBlocks() {
		if (this.catchBlocks == null) {
			this.catchBlocks = new ArrayList<CatchBlock>();
		}
		return this.catchBlocks;
	}

	public void setCatchBlocks(List<CatchBlock> catchBlocks) {
		this.catchBlocks = catchBlocks;
	}

	/**
	 * <pre>
	 *           1..1          1..1
	 * TryStatement ------------------------> Statement
	 *                   &lt;       finalStatement
	 * </pre>
	 */
	private Statement finalStatement;

	public void setFinalStatement(Statement value) {
		this.finalStatement = value;
	}

	public Statement getFinalStatement() {
		return this.finalStatement;
	}
	
	public void accept(GASTVisitor v) {
		v.visitTryStatement(this);
	}

	public ArrayList<Statement> getEndStmt() {
		return EndStmt;
	}

	public void setEndStmt(ArrayList<Statement> endStmt) {
		EndStmt = endStmt;
	}

}
