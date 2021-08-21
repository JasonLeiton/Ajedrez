package ASTMCore.ASTMSyntax.Statement;

import java.util.ArrayList;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Modifiers;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.FOR_CHECK_AFTER_STATEMENT;

public class ForCheckAfterStatement extends ForStatement {

	public ForCheckAfterStatement() {
		setTag(FOR_CHECK_AFTER_STATEMENT);
	}

	public void accept(GASTVisitor v) {
		v.visitForCheckAfterStatement(this);
	}
	
	private ArrayList<Statement> EndStmt;
	
	public ArrayList<Modifiers> getModifiers() {
		return modifiers;
	}

	public void setModifiers(ArrayList<Modifiers> modifiers) {
		this.modifiers = modifiers;
	}

	public ArrayList<Statement> getEndStmt() {
		return EndStmt;
	}

	public void setEndStmt(ArrayList<Statement> arrayList) {
		EndStmt = arrayList;
	}

	private ArrayList<Modifiers> modifiers;
}
