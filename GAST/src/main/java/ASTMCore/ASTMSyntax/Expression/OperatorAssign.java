package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.OPERATOR_ASSIGN;

public class OperatorAssign extends BinaryOperator {

	public OperatorAssign() {
		setTag(OPERATOR_ASSIGN);
	}

	private BinaryOperator operator;

	public void setOperator(BinaryOperator value) {
		this.operator = value;
	}

	public BinaryOperator getOperator() {
		return this.operator;
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}

}
