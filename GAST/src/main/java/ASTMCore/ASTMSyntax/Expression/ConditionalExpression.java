package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.CONDITIONAL_EXPRESSION;

public class ConditionalExpression extends Expression {

	public ConditionalExpression() {
		setTag(CONDITIONAL_EXPRESSION);
	}

	private Expression condition;

	public void setCondition(Expression value) {
		this.condition = value;
	}

	public Expression getCondition() {
		return this.condition;
	}


	private Expression onTrueOperand;

	public void setOnTrueOperand(Expression value) {
		this.onTrueOperand = value;
	}

	public Expression getOnTrueOperand() {
		return this.onTrueOperand;
	}


	private Expression onFalseOperand;

	public void setOnFalseOperand(Expression value) {
		this.onFalseOperand = value;
	}

	public Expression getOnFalseOperand() {
		return this.onFalseOperand;
	}

	public void accept(GASTVisitor v) {
		v.visitConditionalExpression(this);
	}
	
}
