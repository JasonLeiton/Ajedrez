package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.VariableDefinition;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.VARIABLE_EXPRESSION;

public class VariableExpression extends Expression {

	public VariableExpression() {
		setTag(VARIABLE_EXPRESSION);
	}

	private VariableDefinition variable;

	public VariableDefinition getVariable() {
		return variable;
	}

	public void setVariable(VariableDefinition variable) {
		this.variable = variable;
	}
	
	public void accept(GASTVisitor v) {
		v.visitVariableExpression(this);
	}
	
}
