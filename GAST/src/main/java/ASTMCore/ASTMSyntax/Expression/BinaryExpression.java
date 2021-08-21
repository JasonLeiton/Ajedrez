package ASTMCore.ASTMSyntax.Expression;

import java.util.List;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.BINARY_EXPRESSION;

public class BinaryExpression extends Expression {

	public BinaryExpression() {
		setTag(BINARY_EXPRESSION);
	}

	private BinaryOperator operator;

	public void setOperator(BinaryOperator value) {
		this.operator = value;
	}

	public BinaryOperator getOperator() {
		return this.operator;
	}

	private Expression leftOperand;

	public void setLeftOperand(Expression value) {
		this.leftOperand = value;
	}

	public Expression getLeftOperand() {
		return this.leftOperand;
	}


	private Expression rightOperand;

	public void setRightOperand(Expression value) {
		this.rightOperand = value;
	}

	public Expression getRightOperand() {
		return this.rightOperand;
	}
	private List<Expression> extendOperands;

	public List<Expression> getExtendOperands() {
		return extendOperands;
	}

	public void setExtendOperands(List<Expression> extendOperands) {
		this.extendOperands = extendOperands;
	}

	public void accept(GASTVisitor v) {
		v.visitBinaryExpression(this);
	}
	
}
