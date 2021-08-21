package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ACTUAL_PARAMETER_EXPRESSION;

public class ActualParameterExpression extends ActualParameter {

	public ActualParameterExpression() {
		setTag(ACTUAL_PARAMETER_EXPRESSION);
	}

	private Expression value;

	public void setValue(Expression value) {
		this.value = value;
	}

	public Expression getValue() {
		return this.value;
	}

	@Override
	public void accept(GASTVisitor v) {
		v.visitActualParameterExpression(this);
	}

}
