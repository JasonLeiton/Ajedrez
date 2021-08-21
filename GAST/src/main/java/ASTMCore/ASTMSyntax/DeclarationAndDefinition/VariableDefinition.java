package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import java.util.List;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.VARIABLE_DEFINITION;

public class VariableDefinition extends DataDefinition {

	// private Expression variable;

	private Expression variable;

	public VariableDefinition() {
		setTag(VARIABLE_DEFINITION);
	}

	private List<Fragment> fragments;

	private List<Modifiers> modifiers;

	public List<Fragment> getFragments() {
		return fragments;
	}

	public void setFragments(List<Fragment> fragments) {
		this.fragments = fragments;
	}

	public List<Modifiers> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<Modifiers> modifiers) {
		this.modifiers = modifiers;
	}

	@Override
	public void accept(GASTVisitor v) {
		v.visitVariableDefinition(this);

	}

	public Expression getVariable() {
		return variable;
	}

	public void setVariable(Expression variable) {
		this.variable = variable;
	}

}
