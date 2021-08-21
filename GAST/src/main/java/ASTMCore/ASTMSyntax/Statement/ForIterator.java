package ASTMCore.ASTMSyntax.Statement;

import java.util.ArrayList;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.FormalParameterDefinition;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Modifiers;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.FOR_ITERATOR;

public class ForIterator extends ForStatement {

	private ArrayList<Statement> EndStmt;

	private ArrayList<Modifiers> modifiers;

	public ForIterator() {
		setTag(FOR_ITERATOR);
	}

	private FormalParameterDefinition parameter;

	public FormalParameterDefinition getParameter() {
		return parameter;
	}

	private ArrayList<FormalParameterDefinition> parameters;

	public ArrayList<FormalParameterDefinition>  getParameters() {
		return parameters;
	}
	
	public void setParameters(ArrayList<FormalParameterDefinition> parameters) {
		this.parameters = parameters;
	}
	
	public void setParameter(FormalParameterDefinition parameter) {
		this.parameter = parameter;
	}
	
	public void accept(GASTVisitor v) {
		v.visitForIterator(this);

	}

	public ArrayList<Modifiers> getModifiers() {
		return modifiers;
	}

	public void setModifiers(ArrayList<Modifiers>  modifiers) {
		this.modifiers = modifiers;
	}

	public ArrayList<Statement> getEndStmt() {
		return EndStmt;
	}

	public void setEndStmt(ArrayList<Statement> endStmt) {
		EndStmt = endStmt;
	}
	
}
