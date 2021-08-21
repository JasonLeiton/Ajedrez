package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.GENERIC_OPERATOR;

public class GenericOperator extends BinaryOperator{

	public GenericOperator() {
		setTag(GENERIC_OPERATOR);
	}

	private String simbol;

	public String getSimbol() {
		return simbol;
	}

	public void setSimbol(String simbol) {
		this.simbol = simbol;

	}
	
	public void accept(GASTVisitor v) {
		v.visitGenericOperator(this);
	}
	
}