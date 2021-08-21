package ASTMCore.ASTMSyntax.Statement;

import java.util.ArrayList;

import ASTMCore.ASTMSemantics.BlockScope;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.BLOCK_STATEMENT;

public class BlockStatement extends Statement {

	public BlockStatement() {
		setTag(BLOCK_STATEMENT);
	}

	/**
	 * <pre>
	 *           1..1          1..*
	 * BlockStatement ------------------------> Statement
	 *                   &lt;       subStatements
	 * </pre>
	 */
	
	private ArrayList<Statement> subStatements;

	public void setSubStatements(ArrayList<Statement> subStatements) {
		this.subStatements = subStatements;
	}

	public ArrayList<Statement> getSubStatements() {
		if (this.subStatements == null) {
			this.subStatements = new ArrayList<Statement>();
		}
		return this.subStatements;
	}

	/**
	 * <pre>
	 *           1..1          1..1
	 * BlockStatement ------------------------> BlockScope
	 *                   &lt;       opensScope
	 * </pre>
	 */
	private BlockScope opensScope;

	public void setOpensScope(BlockScope value) {
		this.opensScope = value;
	}

	public BlockScope getOpensScope() {
		if (this.opensScope==null)
			this.opensScope=new BlockScope();
		return this.opensScope;
	}
	
	@Override
	public void accept(GASTVisitor v) {
		v.visitBlockStatement(this);
	}

}
