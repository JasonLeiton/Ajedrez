package ASTMCore.ASTMSyntax.Expression;


import ASTMCore.ASTMSyntax.Types.TypeReference;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.INSTANCE_OF_EXPRESSION;

public class InstanceOfExpression extends Expression {

	public InstanceOfExpression() {
		setTag(INSTANCE_OF_EXPRESSION);
	}

	private Expression leftOperand;
	private TypeReference rightOperand;
	
	public Expression getLeftOperand() {
		return leftOperand;
	}
	
	public void setLeftOperand(Expression leftOperand) {
		this.leftOperand = leftOperand;
	}
	
	public TypeReference getRightOperand() {
		return rightOperand;
	}
	
	public void setRightOperand(TypeReference rightOperand) {
		this.rightOperand = rightOperand;
	}

	public void accept(GASTVisitor v) {
		v.visitInstanceOfExpression(this);
	}
		
}
